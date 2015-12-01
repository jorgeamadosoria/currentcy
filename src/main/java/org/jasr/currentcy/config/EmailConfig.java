package org.jasr.currentcy.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
class EmailConfig {

    @Value("${MAIL_HOST}")
    private String host;
    @Value("${MAIL_PORT}")
    private String port;
    @Value("${MAIL_USERNAME}")
    private String username;
    @Value("${MAIL_PASSWORD}")
    private String password;
    @Value("${MAIL_SMTP_AUTH}")
    private String auth;
    @Value("${MAIL_SMTP_STARTTLS_ENABLE}")
    private String tls;
    @Value("${MAIL_DEBUG}")
    private String debug;
    
	@Bean
	public JavaMailSenderImpl mailSender() {
	    JavaMailSenderImpl bean = new JavaMailSenderImpl();
	    bean.setHost(host);
	    bean.setPort(Integer.valueOf(port));
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