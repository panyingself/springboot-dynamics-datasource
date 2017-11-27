package com.py.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



/**
 * desc: 数据源切面配置
 * Created by jack-cooper on 2017/1/18.
 */
@Aspect
@Order(-1)
@Component
public class DataSourceAop {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.py.service.write..*.*(..))")
    public void setCenterUserDataSourceType() throws Exception {
        DataSourceContextHolder.setDataSourceType(DataSourceType.master.getType());
        log.info("dataSource == >: write  写库");
    }

    @Before("execution(* com.py.service.read..*.*(..))")
    public void setUserCenterDataSourceType() throws Throwable{
        DataSourceContextHolder.setDataSourceType(DataSourceType.slave.getType());
        log.info("dataSource == >：read  读库");
    }


    @After("execution(* com.py.service.*..*.*(..)) ")
    public void afterReturning() throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
        log.info("=====> clear dataSource aop ");
    }
}

