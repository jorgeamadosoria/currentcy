package org.jasr.currentcy.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Value("${MAIL_USERNAME}")
    private String username;
    @Value("${MAIL_PASSWORD}")
    private String password;
    

    @Bean
    public freemarker.template.Configuration configuration() {
    	freemarker.template.Configuration bean = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
    	bean.setClassForTemplateLoading(this.getClass(),"/");
    	bean.setDefaultEncoding("UTF-8");
    	return bean;
    }
    
	@Bean
	public JavaMailSenderImpl mailSender() {
	    JavaMailSenderImpl bean = new JavaMailSenderImpl();
	    bean.setHost("smtp.gmail.com");
	    bean.setPort(465);
	    bean.setUsername(username);
	    bean.setPassword(password);
	    Properties javaMailProperties = new Properties();
	    javaMailProperties.put("mail.transport.protocol", "smtps");
	    javaMailProperties.put("mail.smtps.auth", "true");
	    javaMailProperties.put("mail.smtps.starttls.enable", "true");
	    javaMailProperties.put("mail.debug", "true");
	    bean.setJavaMailProperties(javaMailProperties);
 
	    return bean;
	}
	
}