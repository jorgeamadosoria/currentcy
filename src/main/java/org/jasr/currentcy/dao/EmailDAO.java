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
     * Stores an email address for subscription to notifications for a specific exchange. If the email doesn't exist, a token is
     * generated and returned. If it exists, the previously generated token is returned.
     * @param code for the exchange to subscribe to
     * @param email the address of the user that wants to subscribe
     * @return the token corresponding to the newly created or pre-existing email address
     */
    public String subscribeEmail(String code, String email);

    public void registerEmail(String token);

    /**
     * Unregisters the email address associated to this token. Notice that one email address can have several tokens associated to
     * it, one for each exchange the user has subscribed to. Any of this tokens can be used to remove all the users subscriptions
     * in one call to this method
     * 
     * @param token
     *            a token associated to an email address
     */
    public void unregisterEmail(String token);

    public String tokenByEmail(String email);

    public String emailByToken(String token);

    /**
     * Gets a list of email addresses for users subscribed to changes on the rates of the exchange identified by the code
     * specified.
     * 
     * @param code
     *            the code of the exchange with the modified rates
     * @return the list of email addresses to send changes to
     */
    public List<Email> getEmailsForNotification(String code);

}
