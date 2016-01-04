package org.jasr.currentcy.service;

import java.util.List;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;

/**
 * Email Service for email persistence and sending. Methods serving as thin wrappers for methods in EmailDAO are left uncommented
 * 
 */
public interface EmailService {
    /**
     * Method to send emails when the exchange rate changes. The change is detected when taking the periodic snapshot of all
     * exchanges. The method loops through all the currently subscribed users to a specific exchange and send personalized emails
     * to each of them. The method is not terribly efficient, as it does not batch the emails nor does it paginates the list of
     * email addresses it takes from the database. This method executes asynchronously, once for each currency whenever a snapshot
     * is taken.
     * 
     * @param currency
     *            the currency that modified its value on the exchanges selected
     * @param changes
     *            the list of samples from exchanges taken from the snapshot that showed modifications on their rates
     */
    public void sendUpdateEmails(Currencies currency, List<Sample> changes);

    public void registerEmail(String email);

    public void unregisterEmail(String token);

    /**
     * Method to send the subscription email to users. If the user has already subscribed, the email will be resent with the same
     * token. Repeating the subscription process has no new effect.
     */
    public void subscribeEmail(String token);

    public void sendRegisterEmail(String email, String token);
}
