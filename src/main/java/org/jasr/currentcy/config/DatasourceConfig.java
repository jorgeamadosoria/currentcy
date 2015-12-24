package org.jasr.currentcy.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfig {

    @Value("${OPENSHIFT_MYSQL_DB_HOST}")
    private String host;
    @Value("${OPENSHIFT_MYSQL_DB_PORT}")
    private String port;
    @Value("${OPENSHIFT_MYSQL_DB_USERNAME}")
    private String username;
    @Value("${OPENSHIFT_MYSQL_DB_PASSWORD}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://"+host+":"+port+"/currentcy";
        config.setJdbcUrl(url);
        config.setUsername(username);	
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.setConnectionTestQuery("select * from snapshot");
        return new HikariDataSource(config);
    }

}
