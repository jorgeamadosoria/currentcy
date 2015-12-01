package org.jasr.currentcy.dao;

import java.util.List;

public interface EmailDAO {

    public String registerEmail(String email);

    public void unregisterEmail(String token);

    public String tokenByEmail(String email);

    public List<String> getEmailBatchForNotification();
}
