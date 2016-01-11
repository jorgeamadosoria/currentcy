package org.jasr.currentcy.domain;

/**
 * Domain instance created to better handle email notifications on the system. Each instance contains an user email address and secret, unique
 * token assigned to this address upon subscription
 */
public class Email {
    private String token;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }

}
