package org.jasr.currentcy.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Email configuration, externalized to a properties file. User credentials are externalized to environment variables to support Openshift deployment
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfig {

    @Value("${MAIL_USERNAME}")
    private String username;
    @Value("${MAIL_PASSWORD}")
    private String password;

    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.port}")
    private String mailPort;
    @Value("${mail.transport.protocol}")
    private String mailTransportProtocol;
    @Value("${mail.smtp.auth}")
    private String mailSmtpAuth;
    @Value("${mail.smtp.starttls.enable}")
    private String mailStartTls;
    @Value("${mail.debug}")
    private String mailDebug;
    

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration bean = new freemarker.template.Configuration(
                freemarker.template.Configuration.VERSION_2_3_23);
        bean.setClassForTemplateLoading(this.getClass(), "/");
        bean.setDefaultEncoding("UTF-8");
        return bean;
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setHost(mailHost);
        bean.setPort(Integer.parseInt(mailPort));
        bean.setUsername(username);
        bean.setPassword(password);
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.transport.protocol", mailTransportProtocol);
        javaMailProperties.put("mail.smtps.auth", mailSmtpAuth);
        javaMailProperties.put("mail.smtps.starttls.enable", mailStartTls);
        javaMailProperties.put("mail.debug", mailDebug);
        bean.setJavaMailProperties(javaMailProperties);

        return bean;
    }

}
