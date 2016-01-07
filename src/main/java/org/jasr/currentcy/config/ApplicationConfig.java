package org.jasr.currentcy.config;

import org.jasr.currentcy.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

/**
 * Standard application configuration including all Spring specific infrastructure such as scheduling, component scanning,
 * properties files and such
 *
 */
@Configuration
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({ Controller.class, Configuration.class }) )
@EnableScheduling
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
