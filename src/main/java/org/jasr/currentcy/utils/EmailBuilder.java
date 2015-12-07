package org.jasr.currentcy.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class EmailBuilder {

	@Resource
	private String fromAddress = "darksoul.uci@gmail.com";
	@Resource
	private String registerSubject = "Welcome to currentcy";
	
	@Resource
	private Configuration configuration;

	public void getRegisterEmail(String email, String token) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromAddress);
		msg.setTo(email);
		msg.setSubject(registerSubject);
		msg.setText(getRegisterEmailBody(token));
}
	
	public String getRegisterEmailBody(String token) {
		try {
			Template template = configuration.getTemplate("register.ftl");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("token", token);
			Writer out = new StringWriter();
			template.process(data, out);
			out.flush();
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
