package org.jasr.currentcy.service.impl;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private EmailDAO           emailDAO;
    @Resource
    private JavaMailSenderImpl mailSender;
    @Resource
    private SamplerService     samplerService;
    @Value("${mail.sender.address}")
    private String mailSenderAddress;
    
    @Resource
    private EmailBuilder       emailBodyUtils;

    
    @Override
    @Async
    public void sendUpdateEmails(Currencies currency, List<Sample> changes) {
        try {
            for (Sample sample : changes) {

                // subscribed users to this exchange
                List<Email> emails = emailDAO.getEmailsForNotification(sample.getCode());
                while (!CollectionUtils.isEmpty(emails)) {
                    String body = emailBodyUtils.getUpdateEmailBody(currency, sample);
                    for (Email email : emails) {
                        // this [[TOKEN]] is not a freemarker variable because it
                        // changes with each recipient, but the rest of the
                        // email remains the same, so I guess this is more efficient
                        String bodyEmail = body.replace("[[TOKEN]]", email.getToken());

                        doSendEmail(email.getEmail(), null, "Currentcy update", bodyEmail);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Could not notify suscribers");
            e.printStackTrace();
        }
    }

    /**
     * Actual work to send the email. A custom MailPreparator is used as recommended by Spring documentation to deal with Gmail
     * TLS
     * 
     * @param to
     *            Recipient of the email.
     * @param bcc
     *            Recipients of the email, if this is a mass message. bcc is used to preserve anonymity
     * @param subject
     *            Email subject
     * @param text
     *            Email text body in HTML format
     */
    private void doSendEmail(String to, List<Email> bcc, String subject, String text) {
        CurrentcyMailPreparator prepare = new CurrentcyMailPreparator(mailSenderAddress,to, bcc, subject, text);
        mailSender.send(prepare);
    }

    public void sendRegisterEmail(String email, String token) {
        doSendEmail(email, null, "Mail register", emailBodyUtils.getRegisterEmailBody(token));
    }

    
    @Override
    public void subscribeEmail(String code,String email) {
        String token = emailDAO.subscribeEmail(code,email);
        sendRegisterEmail(email, token);
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
