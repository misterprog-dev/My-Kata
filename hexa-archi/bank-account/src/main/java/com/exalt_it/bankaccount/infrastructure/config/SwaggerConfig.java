package com.exalt_it.bankaccount.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI defineAuthBearer() {
        return new OpenAPI().info(getInfo());
    }

    private Info getInfo() {
        Contact contact = new Contact();
        contact.setEmail("soum.diakite@outlook.com");
        contact.setName("Soumaila DIAKITE");

        License mitLicense = new License().name("MIT License")
                .url("<https://choosealicense.com/licenses/mit/>");

        return new Info().title("Exalt IT Banking Kata API")
                .contact(contact)
                .version("0.0.1")
                .description("This API exposes Banking operations")
                .license(mitLicense);
    }
}
