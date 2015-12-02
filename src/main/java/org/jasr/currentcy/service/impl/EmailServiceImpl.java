package org.jasr.currentcy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.dao.EmailDAO;
import org.jasr.currentcy.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class EmailServiceImpl implements EmailService {

	@Resource
	private EmailDAO emailDAO;
	@Resource
	private JavaMailSenderImpl mailSender;

	@Override
	public void sendEmails() {
		emailDAO.setEmailsToNotNotified();
		List<String> emails = emailDAO.getEmailBatchForNotification();
		while (!CollectionUtils.isEmpty(emails)) {
			sendEmail(emails);
			System.out.println("batch of email notifications " + emails.size());
			emails = emailDAO.getEmailBatchForNotification();
		}
	}

	private String getEmailBody() {
		return null;
	}

	private void sendEmail(List<String> emails) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("Currentcy");
		msg.setBcc(emails.toArray(new String[0]));
		msg.setSubject("Exchange Update");
		msg.setText(getEmailBody());
		mailSender.send(msg);
	}

	@Override
	public void registerEmail(String email) {
		emailDAO.registerEmail(email);
	}

	@Override
	public void unregisterEmail(String token) {
		emailDAO.unregisterEmail(token);
	}

}
