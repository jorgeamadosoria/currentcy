package org.jasr.currentcy.dao.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.dao.EmailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmailDAOImpl implements EmailDAO {

    @Autowired
    private Environment       env;
    @Resource
    private JdbcTemplate      template;
    @Resource
    public JavaMailSenderImpl mailSender;

    private static final int  BATCH_LIMIT = 100;

    private SecureRandom      random      = new SecureRandom();

    private String token() {
        return new BigInteger(130, random).toString(32);
    }

    @Override
    public String registerEmail(String email) {
        String token = null;
        do {
            token = token();
        }
        while (template.queryForObject(env.getProperty("token.exists"), Boolean.class, token));

        template.update(env.getProperty("save.email"), email, token);
        return token;
    }

    @Override
    public void unregisterEmail(String token) {
        template.update(env.getProperty("delete.email"), token);
    }

    @Override
    public String tokenByEmail(String email) {
        return template.queryForObject(env.getProperty("token.email"), String.class, email);
    }

    @Override
    public List<String> getEmailBatchForNotification() {
        return (List<String>) template.queryForList(env.getProperty("select.emails"), String.class, BATCH_LIMIT);
    }

}
