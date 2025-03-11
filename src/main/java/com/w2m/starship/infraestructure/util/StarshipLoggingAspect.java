package com.w2m.starship.infraestructure.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StarshipLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(StarshipLoggingAspect.class);

    @Pointcut("execution(* com.w2m.starship.infraestructure.adapter.inbound.StarshipController.getStarshipById(..)) && args(id,..)")
    public void getStarshipByIdMethod(Long id) {}

    @Before("execution(* com.w2m.starship.infraestructure.adapter.inbound..StarshipController.getStarshipById(..)) && args(id,..)")
    public void logNegativeId(JoinPoint joinPoint, Long id) {
        if (id < 0) {
            logger.warn("Se ha solicitado una nave con un ID negativo: {}", id);
        }
    }
}
