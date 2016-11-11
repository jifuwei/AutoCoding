<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">


    <!--  JNDI数据源-->
    <!--<jee:jndi-lookup id="dataSource" jndi-name="java:comp/env/${datasource.datasource_name}" />-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
        <property name="driverClass" value="${datasource.database_driver}" />
        <property name="url" value="jdbc:mysql://${datasource.datasource_ip}:${datasource.datasource_port}/${datasource.db_name}?characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull" />
        <property name="username" value="${datasource.db_connect_name}" />
        <property name="password" value="${datasource.db_connect_pwd}" />
    </bean>

    <!-- 使用阿里的druid配置数据源 start-->
    <!--具体查看官网信息：https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_DruidDataSource%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE-->
    <!--<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        &lt;!&ndash; 可独立出配置文件中 &ndash;&gt;
        <property name="url"
                  value="jdbc:mysql://localhost:3306/autocoding?characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>

        &lt;!&ndash; 初始化连接大小 &ndash;&gt;
        <property name="initialSize" value="1"/>
        &lt;!&ndash; 初始化连接池最大使用连接数量 &ndash;&gt;
        <property name="maxActive" value="20"/>
        &lt;!&ndash; 初始化连接池最小空闲 &ndash;&gt;
        <property name="minIdle" value="1"/>

        &lt;!&ndash; 获取连接最大等待时间，单位毫秒&ndash;&gt;
        <property name="maxWait" value="60000"/>

        &lt;!&ndash; 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 &ndash;&gt;
        <property name="timeBetweenEvictionRunsMillis" value="60000"/>
        &lt;!&ndash; 配置一个连接在池中最小生存的时间，单位是毫秒 &ndash;&gt;
        <property name="minEvictableIdleTimeMillis" value="25200000"/>

        &lt;!&ndash; 打开PSCache，并且指定每个连接上PSCache的大小 &ndash;&gt;
        &lt;!&ndash;如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。分库分表较多的数据库，建议配置为false。&ndash;&gt;
        <property name="poolPreparedStatements" value="false"/>
        <property name="maxPoolPreparedStatementPerConnectionSize" value="20"/>

        <property name="validationQuery" value="${r"${validation_query}"}"/>
        <property name="testWhileIdle" value="true"/>
        <property name="testOnBorrow" value="false"/>
        <property name="testOnReturn" value="false"/>


        &lt;!&ndash;当程序存在缺陷时，申请的连接忘记关闭，这时候，就存在连接泄漏了。Druid提供了RemoveAbandanded相关配置，用来关闭长时间不使用的连接&ndash;&gt;
        &lt;!&ndash;配置removeAbandoned对性能会有一些影响，建议怀疑存在泄漏之后再打开。在上面的配置中，如果连接超过30分钟未关闭，就会被强行回收，并且日志记录连接申请时的调用堆栈。&ndash;&gt;
        &lt;!&ndash;具体查看官网信息：https://github.com/alibaba/druid/wiki/%E8%BF%9E%E6%8E%A5%E6%B3%84%E6%BC%8F%E7%9B%91%E6%B5%8B&ndash;&gt;
        &lt;!&ndash; 打开removeAbandoned功能 &ndash;&gt;
        <property name="removeAbandoned" value="true"/>
        &lt;!&ndash; 1800秒，也就是30分钟 &ndash;&gt;
        <property name="removeAbandonedTimeout" value="1800"/>
        &lt;!&ndash; 关闭abanded连接时输出错误日志 &ndash;&gt;
        <property name="logAbandoned" value="true"/>

        &lt;!&ndash; 配置监控统计拦截的filters&ndash;&gt;
        &lt;!&ndash;官网信息：https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatFilter&ndash;&gt;
        &lt;!&ndash;mergeSql可以合并输出的sql，方便查看，但是在mybatis框架中使用这个则无法监控sql,需要用stat&ndash;&gt;
        &lt;!&ndash;<property name="filters" value="mergeSql,log4j"/>&ndash;&gt;
        &lt;!&ndash;<property name="filters" value="mergeSql,wall"/>&ndash;&gt;
        &lt;!&ndash;<property name="filters" value="stat"/>&ndash;&gt;
        &lt;!&ndash;<property name="filters" value="mergeSql"/>&ndash;&gt;
        <property name="filters" value="stat,log4j"/>
    </bean>-->
    <!-- 使用阿里的druid配置数据源 end-->

</beans>