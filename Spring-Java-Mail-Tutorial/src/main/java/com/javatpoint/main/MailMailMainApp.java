package com.javatpoint.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javatpoint.MailMail;

public class MailMailMainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		MailMail mailMail = (MailMail) context.getBean("mailMail");

		String from = "prateek.ashtikar512@gmail.com";
		String to = "prateek.ashtikar@rediffmail.com";

		String subject = "Testing Server Email";
		String msgBody = "This is test Mail";

		//mailMail.sendMailMimeMessagePreparator(from, to, subject, msgBody);
		
		mailMail.sendMailAttachmentBySpringMimeMessageHelper(from, to, subject, msgBody);
		
		System.out.println("Mail Sent Sucessfully...");
	}
}
