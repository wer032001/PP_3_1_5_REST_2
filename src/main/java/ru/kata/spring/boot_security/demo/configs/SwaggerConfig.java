package ru.kata.spring.boot_security.demo.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

//Swagger http://localhost:8080/swagger-ui/index.html#/

@OpenAPIDefinition(
        info = @Info(title = "REST", version = "1.0", description = "Rest")
)
@Configuration
public class SwaggerConfig {
}
