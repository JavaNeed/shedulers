multiple-jobs-in-quartz-spring-example
---------------------------------------
@Ref: 
- https://gist.github.com/jelies/5085593
- https://www.mkyong.com/java/example-to-run-multiple-jobs-in-quartz/


In this example, we show you how to declare multiple Quartz jobs via Quartz APIs, Quartz XML and Spring. In Quartz scheduler framework, each job will be attached to an unique trigger, and run it by scheduler.

P.S In Quartz, one trigger for multiple jobs is not possible. (Correct me if this is wrong.)

Spring-Quartz.xml
------------------
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:jpa="http://www.springframework.org/schema/data/jpa"
	xmlns:repository="http://www.springframework.org/schema/data/repository"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa.xsd
		http://www.springframework.org/schema/data/repository http://www.springframework.org/schema/data/repository/spring-repository.xsd">

	<import resource="classpath:dataSourceContext.xml"/>
	
	<jpa:repositories base-package="com.mkyong.repository" />
	<context:component-scan base-package="com.mkyong.*" />
	<context:annotation-config />

	<bean id="jobA" class="com.mkyong.job.JobA" />
	<bean id="jobB" class="com.mkyong.job.JobB" />
	<bean id="jobC" class="com.mkyong.job.JobC" />
	<bean id="autowiredA" class="com.mkyong.job.JobASpringBeanJobFactory" />

	<!-- ~~~~~~~~~ Quartz Job ~~~~~~~~~~ -->
	<bean name="JobA" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
		<property name="jobClass" value="com.mkyong.job.JobA" />
	</bean>

	<bean name="JobB" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
		<property name="jobClass" value="com.mkyong.job.JobB" />
	</bean>
	
	<bean name="JobC" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
		<property name="jobClass" value="com.mkyong.job.JobC" />
	</bean>
	
	<!-- ~~~~~~~~~~~ Cron Trigger, run every 5 seconds ~~~~~~~~~~~~~ -->
	<bean id="cronTriggerJobA" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<property name="jobDetail" ref="JobA" />
		<property name="cronExpression" value="0/5 * * * * ?" />
	</bean>
	
	<bean id="cronTriggerJobB" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<property name="jobDetail" ref="JobB" />
		<property name="cronExpression" value="0/5 * * * * ?" />
	</bean>
	
	<bean id="cronTriggerJobC" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<property name="jobDetail" ref="JobC" />
		<property name="cronExpression" value="0/5 * * * * ?" />
	</bean>

	<!-- ~~~~~~~~~~~~~~~~  Scheduler bean Factory   ~~~~~~~~~~~~~~~~ -->
	<bean class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
	<property name="jobFactory" ref="autowiredA"/> 
		<property name="triggers">
			<list>
				<ref bean="cronTriggerJobA" />
				<!-- <ref bean="cronTriggerJobB" />
				<ref bean="cronTriggerJobC" /> -->
			</list>
		</property>
	</bean>
</beans>

dataSourceContext.xml
-----------------------
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
        http://camel.apache.org/schema/spring http://camel.apache.org/schema/spring/camel-spring.xsd
        http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc.xsd">
        
    <context:property-placeholder location="classpath:database.properties"/>
    
    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager"/>
    
	<!-- <jdbc:initialize-database data-source="dataSource" enabled="true">
		<jdbc:script location="classpath:db-schema.sql" />
		<jdbc:script location="classpath:db-test-data.sql" />
	</jdbc:initialize-database> -->
	
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="${mysql.driver.class.name}" />
		<property name="url" value="${mysql.url}" />
		<property name="username" value="${mysql.username}" />
		<property name="password" value="${mysql.password}" />
	</bean>
	
	<bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
        <property name="showSql" value="true"/>
        <property name="generateDdl" value="true"/>
        <property name="database" value="${database.vendor}"/>
    </bean>
    
    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        
        <property name="jpaVendorAdapter" ref="jpaVendorAdapter"/>
        
        <!-- spring based scanning for entity classes-->
        <property name="packagesToScan" value="com.mkyong.*"/>
    </bean>

</beans>


