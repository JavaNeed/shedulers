spring-apache-camel
---------------------

@Ref: http://www.mysqltutorial.org/mysql-sample-database.aspx

In this example, we used following:-
- Maven
- Java 8
- Spring 4.3.1.RELEASE
- spring-data-jpa
- hibernate-entitymanager
- camel-core
- camel-spring
- camel-quartz



application-context.xml

```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jpa="http://www.springframework.org/schema/data/jpa"
	xsi:schemaLocation="http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">
	
	<import resource="apache-camel.xml" />
	<import resource="database-context.xml"/>
	
	<bean id="camelScheduler" class="com.scheduler.schedule.CamelScheduler" />

	<!-- Beans for CustomRepository classes -->	
	<bean id="customCustomerRepo" class="com.scheduler.customRepository.CustomerCustomRepositoryImpl" />
</beans>
```

database-context.xml
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jpa="http://www.springframework.org/schema/data/jpa"
	xsi:schemaLocation="http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">

	<context:property-placeholder location="classpath:database.properties" />
	
	<!-- Jpa Repositories -->
	<jpa:repositories base-package="com.scheduler.repository" />

	<bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="${mysql.driver.class.name}" />
		<property name="url" value="${mysql.url}" />
		<property name="username" value="${mysql.username}" />
		<property name="password" value="${mysql.username}" />
	</bean>

	<bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
		<property name="showSql" value="true" />
		<property name="generateDdl" value="${mysql.generate.ddl}" />
		<property name="databasePlatform" value="${mysql.dialect}" />
	</bean>

	<!-- ================= Entity Manager ===================== -->
	<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="dataSource" ref="datasource" />
		
		<property name="packagesToScan" value="com.scheduler.model" />
		
		<property name="jpaVendorAdapter" ref="jpaVendorAdapter"/>
		
		<property name="persistenceUnitName" value="${mysq.persistent.unit.name}" />
		
		<property name="jpaProperties">
		    <props>
		      <prop key="hibernate.show_sql">${hibernate.show.sql}</prop>
		      <prop key="hibernate.hbm2ddl.auto">${hibernate.hbm2ddl.auto}</prop>
		    </props>
  		</property>
	</bean>

	<!-- ==================== Transaction Manager ========================== -->
	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory" />
	</bean>
</beans>
```


apache-camel.xml
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:camel="http://camel.apache.org/schema/spring"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://camel.apache.org/schema/spring http://camel.apache.org/schema/spring/camel-spring.xsd">
	
	<camel:camelContext id="com.scheduler.camelContext" trace="true">
		<camel:contextScan />
	</camel:camelContext>
</beans>
```

