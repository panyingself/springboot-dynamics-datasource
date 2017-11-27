# springboot-dynamics-datasource
springboot动态数据源 aop实现

简要介绍：

通过aop实现AbstractRoutingDataSource接口，动态切换数据源

DataSourceAop：
  
  数据源切面配置,用于规则匹配适用的数据源

DataSourceConfiguration：

  数据源初始化，并且为MyAbstractRoutingDataSource指定默认数据源以及目标数据源

DataSourceContextHolder：

  创建ThreadLocal，用于控制指定数据源以及清空数据源等

DataSourceType：

  数据源枚举类，用于配置制定数据源key

MyAbstractRoutingDataSource：

  规则获取最终数据源

MybatisPlusConfig：

  mybatispluse 配置
