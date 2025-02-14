package com.w2m.starship.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

   @Bean
   public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Starship API")
                        .version("1.0.0-SNAPSHOT")
                        .description("This API is a CRUD for a Starship Object"));
    }
}

