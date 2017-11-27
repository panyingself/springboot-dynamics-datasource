package com.py.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jack-cooper on 2017/1/18.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration implements TransactionManagementConfigurer {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${spring.master.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "masterDataSource", destroyMethod = "close", initMethod="init")
    @ConfigurationProperties(prefix = "spring.master")
    public DataSource masterDataSourceOne() {
        log.info("--------------------  写库 init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "slaveDataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "spring.slave")
    public DataSource slaveDataSourceOne() {
        log.info("-------------------- 从 读库 init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }


    @Bean("dataSources")
    public Map<String,DataSource> dataSources(){
        Map<String,DataSource> dataSources = new HashMap<String, DataSource>();
        dataSources.put(DataSourceType.master.getType(), masterDataSourceOne());
        dataSources.put(DataSourceType.slave.getType(),slaveDataSourceOne());
        return dataSources;
    }


    /**
     * 有多少个数据源就要配置多少个bean
     *
     * @return
     */
    @Bean(name = "routingDataSource")
    public MyAbstractRoutingDataSource roundRobinDataSouceProxy() {
        int size = Integer.parseInt("1");
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DataSourceType.slave.getType(), dataSources());//读库
        for (String type : dataSources().keySet()) {
            targetDataSources.put(type, dataSources().get(type));
        }
        proxy.setDefaultTargetDataSource(masterDataSourceOne());//默认写库
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    /**
     * 事务配置,考虑多数据源情况下
     * @return
     */
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(roundRobinDataSouceProxy());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }
}

