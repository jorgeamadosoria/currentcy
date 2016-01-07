package org.jasr.currentcy.utils;

import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jasr.currentcy.domain.Email;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.CollectionUtils;

/**
 * Preparator for Email with TLS. This is the recommended approach for Spring MVC with GMail TLS. GMail address hardwired for use
 *
 */
public class CurrentcyMailPreparator implements MimeMessagePreparator {

    private String   to;
    private String[] bcc;
    private String   subject;
    private String   text;
    private String senderAddress;

    public CurrentcyMailPreparator(String senderAddress,String to, List<Email> bcc, String subject, String text) {
        if (to != null)
            this.to = to;
        if (!CollectionUtils.isEmpty(bcc))
            this.bcc = bcc.toArray(new String[0]);
        this.subject = subject;
        this.text = text;
        this.senderAddress = senderAddress;
    }

    public void prepare(MimeMessage mimeMessage) throws Exception {
        if (to != null)
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        if (bcc != null) {
            for (String copy : bcc)
                mimeMessage.setRecipient(Message.RecipientType.BCC, new InternetAddress(copy));
        }
        mimeMessage.setFrom(new InternetAddress(senderAddress));
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(text, "text/html; charset=utf-8");
    }
}
