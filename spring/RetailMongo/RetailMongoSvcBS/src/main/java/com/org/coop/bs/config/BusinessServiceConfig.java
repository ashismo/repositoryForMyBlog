package com.org.coop.bs.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(basePackages = "com.org.coop")
@PropertySource("classpath:application.properties")
public class BusinessServiceConfig {
	@Autowired
	private Environment env;
	
	@Value("${email.host}")
	private String emailHost;
	
	@Value("${email.port}")
	private String emailPort;
	
	@Value("${email.username}")
	private String emailUserName;
	
	@Value("${email.password}")
	private String emailPassword;
			
	@Value("${email.transport.protocol}")
	private String emailTransportProtocol;
	
	@Value("${email.smtp.auth}")
	private String emailSmtpAuth;
	
	@Value("${email.smtp.starttls.enable}")
	private String emailSmtpStartTlsEnable;
	
	@Value("${mail.smtp.starttls.required}")
	private String emailSmtpStartTlsRequired;
	
	@Value("${email.debug}")
	private String emailDebug;

	@Bean
	public JavaMailSenderImpl getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		if(emailHost == null || !emailHost.contains("smtp.gmail.com")) {
			emailHost = "smtp.gmail.com";
		}
		
		if(emailPort == null || !emailPort.contains("587")) {
			emailPort = "587";
		}
		
		if(emailUserName == null || !emailUserName.contains("asmo00b1")) {
			emailUserName = "asmo00b1@gmail.com";
		}
		
		if(emailPassword == null || !emailPassword.contains("akmdaldona")) {
			emailPassword = "akmdaldona";
		}
		
		if(emailTransportProtocol == null || !emailTransportProtocol.contains("smtp")) {
			emailTransportProtocol = "smtp";
		}
		
		if(emailSmtpStartTlsRequired == null || !emailSmtpStartTlsRequired.contains("true")) {
			emailSmtpStartTlsRequired = "true";
		}
		
		if(emailSmtpStartTlsEnable == null || !emailSmtpStartTlsEnable.contains("true")) {
			emailSmtpStartTlsEnable = "true";
		}
		
		mailSender.setHost(emailHost);
		mailSender.setPort(Integer.parseInt(emailPort));
		mailSender.setUsername(emailUserName);
		mailSender.setPassword(emailPassword);
		mailSender.setProtocol(emailTransportProtocol);
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", emailTransportProtocol);
		javaMailProperties.put("mail.smtp.auth", emailSmtpAuth);
		javaMailProperties.put("mail.smtp.starttls.enable", emailSmtpStartTlsEnable);
		javaMailProperties.put("mail.smtp.starttls.required", emailSmtpStartTlsRequired);
		javaMailProperties.put("mail.debug", emailDebug);
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

}
