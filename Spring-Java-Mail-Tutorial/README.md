Spring Java Mail Tutorial
-----------------------------
Spring framework provides many useful interfaces and classes for sending and receiving mails.

The org.springframework.mail package is the root package that provides mail support in Spring framework.

Spring Java Mail API
--------------------
The interfaces and classes for java mail support in spring framework are as follows:
1. MailSender interface: It is the root interface. It provides basic functionality for sending simple mails.
2. JavaMailSender interface: It is the subinterface of MailSender. It supports MIME messages. It is mostly used with MimeMessageHelper class for the creation of JavaMail MimeMessage, with attachment etc. The spring framework recommends MimeMessagePreparator mechanism for using this interface.
3. JavaMailSenderImpl class: It provides the implementation of JavaMailSender interface. It supports JavaMail MimeMessages and Spring SimpleMailMessages.
4. SimpleMailMessage class: It is used to create a simple mail message including from, to, cc, subject and text messages.
5. MimeMessagePreparator interface: It is the callback interface for the preparation of JavaMail MIME messages.
6. MimeMessageHelper class: It is the helper class for creating a MIME message. It offers support for inline elements such as images, typical mail attachments and HTML text content.

@Ref: http://www.javatpoint.com/spring-java-mail-tutorial


application.properties:
```
smpt.host=smtp.gmail.com
smtp.username=XXX.XXX@gmail.com
smtp.password=XXXX

mail.smtp.auth=true
mail.smtp.socketFactory.port=465
mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
mail.smtp.port=465
```


applicationContext.xml
---------------------
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

	<context:property-placeholder location="classpath:application.properties" />

	<bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
		<property name="host" value="${smpt.host}" />
		<property name="username" value="${smtp.username}" />
		<property name="password" value="${smtp.password}" />
		<property name="javaMailProperties">
			<props>
				<prop key="mail.smtp.auth">${mail.smtp.auth}</prop>
				<prop key="mail.smtp.socketFactory.port">${mail.smtp.socketFactory.port}</prop>
				<prop key="mail.smtp.socketFactory.class">${mail.smtp.socketFactory.class}</prop>
				<prop key="mail.smtp.port">${mail.smtp.port}</prop>
			</props>
		</property>
	</bean>
	
	
	<bean id="applicationMail" class="com.javatpoint.ApplicationMail">
		<property name="mailSender" ref="mailSender" />
	</bean>
	
	<bean id="mailMail" class="com.javatpoint.MailMail">
		<property name="mailSender" ref="mailSender" />
	</bean>
</beans>
```