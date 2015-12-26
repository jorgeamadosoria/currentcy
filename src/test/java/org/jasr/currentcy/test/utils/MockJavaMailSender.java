package org.jasr.currentcy.test.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MockJavaMailSender extends JavaMailSenderImpl{

    private MimeMessage[] mimeMessages;
    private Object[] originalMessages;
    
    
    
    public MimeMessage[] getMimeMessages() {
        return mimeMessages;
    }



    public void setMimeMessages(MimeMessage[] mimeMessages) {
        this.mimeMessages = mimeMessages;
    }



    public Object[] getOriginalMessages() {
        return originalMessages;
    }



    public void setOriginalMessages(Object[] originalMessages) {
        this.originalMessages = originalMessages;
    }



    @Override
    protected void doSend(MimeMessage[] mimeMessages, Object[] originalMessages) throws MailException {
        this.mimeMessages = mimeMessages;
        this.originalMessages = originalMessages;
    }


}
