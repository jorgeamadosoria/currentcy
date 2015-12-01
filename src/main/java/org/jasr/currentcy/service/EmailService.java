package org.jasr.currentcy.service;

public interface EmailService {

    public void sendEmails();
    
    public void registerEmail(String email);
    public void unregisterEmail(String token);
}
