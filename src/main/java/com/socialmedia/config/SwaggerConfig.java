package com.socialmedia.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI socialMediaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Social Media Backend API")
                        .description("REST API documentation for the Social Media project.\n\n" +
                                "This project provides user authentication, posts, likes, comments, " +
                                "and admin functionalities. Built using Spring Boot, Spring Security (JWT), " +
                                "Spring Data JPA, and MySQL/MongoDB.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Tejas Gajanan Amzare")
                                .url("https://github.com/Tejas-Amzare")   // 🔹 GitHub/LinkedIn link
                                .email("tejasamzare10@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}