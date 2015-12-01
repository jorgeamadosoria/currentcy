package org.jasr.currentcy.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
class EmailConfig {

    @Value("${MAIL_USERNAME}")
    private String username;
    @Value("${MAIL_PASSWORD}")
    private String password;
    
	@Bean
	public JavaMailSenderImpl mailSender() {
	    JavaMailSenderImpl bean = new JavaMailSenderImpl();
	    bean.setHost("smtp.gmail.com");
	    bean.setPort(587);
	    bean.setUsername(username);
	    bean.setPassword(password);
	    Properties javaMailProperties = new Properties();
	    javaMailProperties.put("mail.transport.protocol", "smtp");
	    javaMailProperties.put("mail.smtp.auth", "true");
	    javaMailProperties.put("mail.smtp.starttls.enable", "true");
	    javaMailProperties.put("mail.debug", "true");
	    bean.setJavaMailProperties(javaMailProperties);
 
	    return bean;
	}
	
}