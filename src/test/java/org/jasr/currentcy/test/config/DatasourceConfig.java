package org.jasr.currentcy.test.config;

import javax.sql.DataSource;

import org.jasr.currentcy.test.utils.EmbeddedMysqlDatabase;
import org.jasr.currentcy.test.utils.EmbeddedMysqlDatabaseBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {

    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedMysqlDatabaseBuilder builder = new EmbeddedMysqlDatabaseBuilder();
        EmbeddedMysqlDatabase db = builder
            .addScript("classpath:sql/schema.sql")
            .addScript("classpath:sql/data.sql")
            .build();
        return db;
    }

}
