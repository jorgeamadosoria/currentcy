package org.jasr.currentcy.dao;

import java.util.List;

import org.jasr.currentcy.domain.Email;

/**
 * DAO for Email handling. Most methods are self explanatory and are left uncommented
 * 
 * @author jorge.amado
 *
 */
public interface EmailDAO {
    /**
     * Stores an email address for subscription to notifications. If the email doesn't exist, a token is generated and returned.
     * If it exists, the previously generated token is returned.
     * 
     * @return the token corresponding to the newly created or pre-existing email address
     */
	public String subscribeEmail(String email);
	
    public void registerEmail(String token);

    public void unregisterEmail(String token);

    public String tokenByEmail(String email);
    
    public String emailByToken(String token);
    /**
     * Gets a list of email addresses for users subscribed to changes on the rates of the exchange identified by the code
     * specified.
     * 
     * @param code
     *            the code of the exchange with the modified rates
     */
    public List<Email> getEmailsForNotification(String code);
    
}
