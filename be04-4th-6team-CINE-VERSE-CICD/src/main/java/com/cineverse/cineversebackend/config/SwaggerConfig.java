package com.cineverse.cineversebackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/* 설명. http://localhost:8081/swagger-ui/index.html */
@OpenAPIDefinition (
        info = @Info(title = "CINE-VERSE API 명세서",
                     description = "CINE-VERSE 서비스 API 명세서",
                     version = "v1")
)
@Configuration
public class SwaggerConfig {
}
