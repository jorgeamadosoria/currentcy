package org.jasr.currentcy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * Swagger configuration for the application. This is a separate site that shows documentation for the controllers that are
 * properly annotated
 *
 */
@Configuration
@EnableSwagger
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(webApiInfo());
    }

    private ApiInfo webApiInfo() {
        ApiInfo apiInfo = new ApiInfo("Currentcy Rest API", "Server side rest endpoints for the exchange showcase for Uruguayan pesos against USD and EUR",
                "https://currentcy-jasr.rhcloud.com", "darksoul.uci@gmail.com", "LGPL 2.1",
                "http://www.gnu.org/licenses/old-licenses/lgpl-2.1.en.html");
        return apiInfo;
    }
}
