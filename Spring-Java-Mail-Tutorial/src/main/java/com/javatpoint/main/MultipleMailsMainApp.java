package com.javatpoint.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javatpoint.ApplicationMail;

public class MultipleMailsMainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		ApplicationMail applicationMail = (ApplicationMail) context.getBean("applicationMail");

		String from = "prateek.ashtikar512@gmail.com";
		String to1 = "prateek.ashtikar@rediffmail.com";
		String to2 = "prateek.ashtikar512@yahoo.in";
		String[] to = {to1, to2};
		
		String subject = "Testing Server Email";
		String msgBody = "Hello Tim, Andrew,  How're you doing ? How is your new office ? How is your new work? Are you in Virginia now ?";

		applicationMail.sendMailToMany(from, to, subject, msgBody);
		System.out.println("Mail Sent Sucessfully...");
	}
}
