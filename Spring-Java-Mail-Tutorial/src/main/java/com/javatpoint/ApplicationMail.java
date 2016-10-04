package com.javatpoint;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class ApplicationMail {
	private MailSender mailSender;  
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String from, String to, String subject, String msgBody){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);  
        mailMessage.setTo(to);  
        mailMessage.setSubject(subject);  
        mailMessage.setText(msgBody);
        
        mailSender.send(mailMessage);
	}
	
	public void sendMailToMany(String from, String[] to, String subject, String msgBody){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);  
        mailMessage.setTo(to);  
        mailMessage.setSubject(subject);  
        mailMessage.setText(msgBody);
        
        mailSender.send(mailMessage);
	}
}
