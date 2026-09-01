package org.example.customercare360.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("CustomerCare360")
                        .version("1.0")
                        .description("Customer Care Management System APIs")
                        .contact(new Contact()
                                .name("CustomerCare360 Team")
                                .email("support@customercare360.com")));
    }
}