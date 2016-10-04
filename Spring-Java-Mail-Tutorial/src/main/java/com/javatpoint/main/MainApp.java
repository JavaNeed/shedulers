package com.javatpoint.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javatpoint.ApplicationMail;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		ApplicationMail applicationMail = (ApplicationMail) context.getBean("applicationMail");

		String from = "prateek.ashtikar512@gmail.com";
		String to = "prateek.ashtikar@rediffmail.com";
		String subject = "Testing Server Email";
		String msgBody = "Hello Tim, How're you doing ? How is your new office ?";

		applicationMail.sendMail(from, to, subject, msgBody);
		System.out.println("Mail Sent Sucessfully...");
	}
}
