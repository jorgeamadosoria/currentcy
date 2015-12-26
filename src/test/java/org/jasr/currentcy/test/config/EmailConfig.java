package org.jasr.currentcy.test.config;

import org.jasr.currentcy.test.utils.MockJavaMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Bean
    public freemarker.template.Configuration configuration() {
    	freemarker.template.Configuration bean = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
    	bean.setClassForTemplateLoading(this.getClass(),"/");
    	bean.setDefaultEncoding("UTF-8");
    	return bean;
    }
    
	@Bean
	public JavaMailSenderImpl mailSender() {
	    JavaMailSenderImpl bean = new MockJavaMailSender();
	    return bean;
	}
	
}