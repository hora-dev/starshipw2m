package com.w2m.starship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class} )
public class StarshipApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarshipApplication.class, args);
    }
}
