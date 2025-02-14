package com.w2m.starship.application.messaging;

import com.w2m.starship.domain.model.Starship;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StarshipConsumer {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleStarshipEvent(StarshipEvent event) {
        log.info("Received starship event: {}", event);

        switch (event.getAction()) {
            case "CREATED":
                handleStarshipCreated(event.getStarship());
                break;
            case "UPDATED":
                handleStarshipUpdated(event.getStarship());
                break;
            case "DELETED":
                handleStarshipDeleted(event.getStarship().getId());
                break;
            default:
                log.warn("Unknown action: {}", event.getAction());
        }
    }

    private void handleStarshipCreated(Starship starship) {
        log.info("Processing starship created: {}", starship);
    }

    private void handleStarshipUpdated(Starship starship) {
        log.info("Processing starship updated: {}", starship);
    }

    private void handleStarshipDeleted(Long id) {
        log.info("Processing starship deleted with ID: {}", id);
    }

    private void handleStarshipFindAll(Starship starship) {
        log.info("Processing starship find all: {}", starship);
    }
}
