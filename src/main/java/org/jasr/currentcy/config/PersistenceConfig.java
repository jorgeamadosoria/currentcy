package org.jasr.currentcy.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:sql.properties")
public class PersistenceConfig {


    
    @Resource
    private DataSource dataSource;

    @Bean(initMethod = "migrate")
    Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:sql/");
        flyway.setDataSource(dataSource);
        flyway.migrate();
        return flyway;
    }
    
   

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate bean = new JdbcTemplate(dataSource);
        return bean;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
