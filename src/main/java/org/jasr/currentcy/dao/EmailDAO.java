package org.jasr.currentcy.dao;

import java.util.List;

public interface EmailDAO {

	public String subscribeEmail(String email);
	
    public void registerEmail(String token);

    public void unregisterEmail(String token);

    public String tokenByEmail(String email);

    public List<String> getEmailBatchForNotification(int offset);
    
}
