package org.jasr.currentcy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.dao.EmailDAO;
import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Email;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.service.EmailService;
import org.jasr.currentcy.service.SamplerService;
import org.jasr.currentcy.utils.CurrentcyMailPreparator;
import org.jasr.currentcy.utils.EmailBuilder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class EmailServiceImpl implements EmailService {

	@Resource
	private EmailDAO emailDAO;
	@Resource
	private JavaMailSenderImpl mailSender;
	@Resource
	private SamplerService samplerService;

	@Resource
	private EmailBuilder emailBodyUtils;

	@Override
	public void sendUpdateEmails() {
		try {
			List<List<Sample>> samplesByCurrency = new ArrayList<>();
			for (Currencies cur : Currencies.values()) {
				samplesByCurrency.add(cur.order, samplerService.getSnapshot(cur));
			}
			String body = emailBodyUtils.getUpdateEmailBody(samplesByCurrency);

			int offset = 0;
			List<Email> emails = emailDAO.getEmailBatchForNotification(offset);
			while (!CollectionUtils.isEmpty(emails)) {
				offset += emails.size();

				for (Email email : emails) {
					// this [[TOKEN]] is not a freemarker variable because it
					// changes with each recipient, but the rest of the
					// email remains the same, so I guess this is more efficient
					String bodyEmail = body.replace("[[TOKEN]]", email.getToken());
					doSendEmail(email.getEmail(), null, "Exchange Update", bodyEmail);
				}

				System.out.println("batch of email notifications " + emails.size());
				emails = emailDAO.getEmailBatchForNotification(offset);
			}
		} catch (Exception e) {
			System.out.println("Could not notify suscribers");
			e.printStackTrace();
		}
	}
	
	public void sendUpdateEmail(String token) {
		try {
			List<List<Sample>> samplesByCurrency = new ArrayList<>();
			for (Currencies cur : Currencies.values()) {
				samplesByCurrency.add(cur.order, samplerService.getSnapshot(cur));
			}
			String body = emailBodyUtils.getUpdateEmailBody(samplesByCurrency);

			String email = emailDAO.emailByToken(token);
					// this [[TOKEN]] is not a freemarker variable because it
					// changes with each recipient, but the rest of the
					// email remains the same, so I guess this is more efficient
					String bodyEmail = body.replace("[[TOKEN]]", token);
					doSendEmail(email, null, "Exchange Update", bodyEmail);
		} catch (Exception e) {
			System.out.println("Could not notify suscribers");
			e.printStackTrace();
		}
	}
	

	private void doSendEmail(String to, List<Email> bcc, String subject, String text) {
		CurrentcyMailPreparator prepare = new CurrentcyMailPreparator(to, bcc, subject, text);
		mailSender.send(prepare);
	}

	public void sendRegisterEmail(String email, String token) {
		doSendEmail(email, null, "Mail register", emailBodyUtils.getRegisterEmailBody(token));
	}

	@Override
	public void subscribeEmail(String email) {
		String token = emailDAO.subscribeEmail(email);
		if (token != null)
			sendRegisterEmail(email, token);
	}

	@Override
	public void registerEmail(String token) {
		emailDAO.registerEmail(token);
		sendUpdateEmail(token);
	}

	@Override
	public void unregisterEmail(String token) {
		emailDAO.unregisterEmail(token);
	}

}
