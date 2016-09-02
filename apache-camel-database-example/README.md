camelHelloWorld
------------------
In this article, we will see some examples of Apache camel timer component.

The timer: component is used to generate message exchanges when a timer fires.

The message exchange can be created on-the-fly by setting its body or by invoking a bean which will return us the message body. Once the message is created, we can either log it, or pass it to other endpoints like a jdbc endpoint to poll a table at a regular interval.

We can setup our routing to do all this and then use a timer to to generate an event at regular interval to keep triggering the routing.

Before we start with our example, Let’s look into the setup details.

This example uses the following frameworks:

- Maven 3.2.3
- Apache Camel 2.17.1
- Spring 4.3.1.RELEASE
- Eclipse  as the IDE, version Luna 4.4.1.

Time Component URI Format

Its URI format is:

?
1
timer:name[?options]
name is the Timer object’s name and options can be used to configure the timer, for example, period=2s will generate periodic events every 2 seconds.


The timer component is the core component of camel so all you need to add is camel-core to your pom.xml dependencies. We will also add few more dependencies as our examples need those.

1. camel-stream – We will use this to send output to the console.
2. camel-jms and activemq-camel – ActiveMQ JMS components.
3. spring-context and camel-spring – Since we be configuring our camel context in spring.
4. mysql-connector-java MySQL Driver.
5. camel-jdbc to access the Camel JDBC component.
6. spring-jdbc to configure JDBC resources in spring like DataSource

@Ref: http://javarticles.com/2015/05/apache-camel-log-component-examples.html