package org.jasr.currentcy.service;

public interface EmailService {

	public void sendUpdateEmails();
    public void registerEmail(String email);
    public void unregisterEmail(String token);
    public void subscribeEmail(String token);
    public void sendRegisterEmail(String email, String token);
}
