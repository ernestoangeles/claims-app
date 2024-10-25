package com.example.claims.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI claimsAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Claims Management API")
                        .description("REST API for managing customer claims")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Claims Team")
                                .email("support@example.com")));
    }
}
