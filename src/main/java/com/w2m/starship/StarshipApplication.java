package com.w2m.starship;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class} )
@EnableCaching
public class StarshipApplication {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;


    public static void main(String[] args) {
        SpringApplication.run(StarshipApplication.class, args);
    }

    @PostConstruct
    public void logRedisConfig() {
        log.info("Conectando a Redis en {}:{}", redisHost, redisPort);
    }
}
