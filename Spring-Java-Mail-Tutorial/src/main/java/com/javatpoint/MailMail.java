package com.javatpoint;

import java.io.File;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailMail {

	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMailMimeMessagePreparator(final String from, final String to, final String subject,
			final String msg) {

		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				mimeMessage.setFrom(new InternetAddress(from));
				mimeMessage.setSubject(subject);
				mimeMessage.setText(msg);
			}
		};
		mailSender.send(messagePreparator);
	}

	public void sendMailAttachmentBySpringMimeMessageHelper(final String from, final String to, final String subject,final String msg) {
		try {
			MimeMessage message = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg);

			// attach the file
			FileSystemResource file = new FileSystemResource(new File("C:/Users/prateek/Desktop/TestCase.png"));
			helper.addAttachment("TestCase.png", file);// image will be sent by
			
			// this name
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
