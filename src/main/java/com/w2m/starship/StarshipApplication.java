package com.w2m.starship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class} )
@EnableCaching  // 🔥 Activa el caché en Spring Boot
public class StarshipApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarshipApplication.class, args);
    }
}
