package com.example.fuelmanagement.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fuel Quota Management System API")
                        .version("1.0")
                        .description("API documentation for Fuel Quota Management System"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth")) // Specify the security requirement
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .name("BearerAuth")
                                .type(Type.HTTP)
                                .in(In.HEADER)
                                .scheme("bearer")
                                .description("Please enter 'Bearer' [space] and then your token in the text input below.\n\nExample: 'Bearer your_token'"))); // Define the security scheme
    }
}

