package org.jasr.currentcy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

//    @Bean //Don't forget the @Bean annotation
//    public SwaggerSpringMvcPlugin customImplementation(){
//       return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
//             .apiInfo(apiInfo())
//             .includePatterns(".*");
//    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "News API",
                "Mobile applications and beyond!",
                "https://helloreverb.com/terms/",
                "matt@raibledesigns.com",
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }
}
