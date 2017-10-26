# 运行本示例
1. 执行script目录下得sql脚本
2. 修改filter/local.properties文件中的数据库配置
3. 在工程目录（web-template目录下）执行 mvn clean install -Plocal 对代码进行编译 （-P后面的local表示执行环境）
4. 启动tomcat或者jetty
5. 在浏览器中访问(参数要根据实际情况进行修改)：
    1. http://localhost:8080/api/user/add?userName=xxx
    2. http://localhost:8080/api/user/get?uid=1




# 模块介绍

- commons: 存放一些公共的工具类等

- dao：数据访问层，data access object

- manager：事务管理层
    1. 在这一层做事务控制
- service：服务暴露层
    1. 进行缓存，中间件等处理
- controller：web-api
    1. 主要提供数据接口，
    2. 只负责对输入参数的基本校验以及对结果进行组合，其他所有工作交给service层完成
    3. 所用的controller必须继承BaseController
    
- 层之间调用关系：controller-->service-->manager-->dao，不允许反向依赖

# 一些约束
- redis：不允许使用spring提供的Template工具，因为这个工具会存储额外的信息用来进行序列化和反序列化等操作
- mongo：同redis的使用，使用原生驱动进行操作
- 日志：初始化log实例的时候不允许会用具体的日志实现系统，应该使用日志框架的api，比如slf4j，建议采用lombok的 @slf4j 注解
- 异常：尽量不要使用异常来控制程序执行流程



# 数据库约束

1. 每张表都有两个必须字段，record_update_time ， record_create_time ，sql:
    ```
    record_update_time timestamp(6) default CURRENT_TIMESTAMP(6) not null ON UPDATE current_timestamp(6) comment '更新时间',
    record_create_time timestamp(6) default CURRENT_TIMESTAMP(6) not null
    ```
2. 除了 record_update_time ，record_create_time 两个字段外，其他所有和时间相关的字段尽量采用 bigint（可根据实际情况适当调整，但不建议使用date,datetime类型）
3. 字段全部采用小写，单词间用下划线分割



# 其他约束
- 可以参考 doc/阿里巴巴Java开发手册.pdf 文件





