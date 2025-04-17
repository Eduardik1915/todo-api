package com.example.todo_api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("ToDo API")
                .description("Simple ToDo app with Spring Boot")
                .version("1.0.0"));
    }
}
