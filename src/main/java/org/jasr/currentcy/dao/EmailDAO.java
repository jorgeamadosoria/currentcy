package org.jasr.currentcy.dao;

import java.util.List;

import org.jasr.currentcy.domain.Email;

public interface EmailDAO {

	public String subscribeEmail(String email);
	
    public void registerEmail(String token);

    public void unregisterEmail(String token);

    public String tokenByEmail(String email);

    public List<Email> getEmailBatchForNotification(int offset);
    
}
