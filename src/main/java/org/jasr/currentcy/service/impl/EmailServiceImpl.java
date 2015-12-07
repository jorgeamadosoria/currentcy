package org.jasr.currentcy.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jasr.currentcy.dao.EmailDAO;
import org.jasr.currentcy.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailServiceImpl implements EmailService {

	@Resource
	private EmailDAO emailDAO;
	@Resource
	private JavaMailSenderImpl mailSender;

	@Resource
	private Configuration configuration;

	@Override
	public void sendEmails() {
		try {
			int offset = 0;
			List<String> emails = emailDAO.getEmailBatchForNotification(offset);
			while (!CollectionUtils.isEmpty(emails)) {
				offset += emails.size();
				sendEmail(emails);
				System.out.println("batch of email notifications " + emails.size());
				emails = emailDAO.getEmailBatchForNotification(offset);
			}
		} catch (Exception e) {
			System.out.println("Could not notify suscribers");
			e.printStackTrace();
		}
	}

	private String getEmailBody() {
		return null;
	}

	private void sendEmail(List<String> emails) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("darksoul.uci@gmail.com");
		msg.setBcc(emails.toArray(new String[0]));
		msg.setSubject("Exchange Update");
		msg.setText(getEmailBody());
		mailSender.send(msg);
	}

	public void sendEmail(String email, String token) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("darksoul.uci@gmail.com");
			msg.setTo(email);
			msg.setSubject("Mail register");
			Template template = configuration.getTemplate("register.ftl");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("token", token);
			Writer out = new StringWriter();
			template.process(data, out);
			out.flush();
			msg.setText(out.toString());
			mailSender.send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void subscribeEmail(String email) {

		String token = emailDAO.subscribeEmail(email);
		sendEmail(email, token);

	}

	@Override
	public void registerEmail(String token) {
		emailDAO.registerEmail(token);
	}

	@Override
	public void unregisterEmail(String token) {
		emailDAO.unregisterEmail(token);
	}

}
