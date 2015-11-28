package com.org.coop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
	@Autowired
	private MailSender emailSender; // MailSender interface defines a strategy
										// for sending simple mails
 
	public void sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom(fromAddress);
		email.setTo(toAddress);
		email.setSubject(subject);
		email.setText(msgBody);
		emailSender.send(email);
	}
}
