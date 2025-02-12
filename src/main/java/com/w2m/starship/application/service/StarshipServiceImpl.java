package com.w2m.starship.application.service;

import com.w2m.starship.application.messaging.StarshipEvent;
import com.w2m.starship.application.port.in.StarshipUseCase;
import com.w2m.starship.domain.model.Starship;
import com.w2m.starship.infraestructure.adapter.outbound.StarshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
@Slf4j
public class StarshipServiceImpl implements StarshipUseCase {

    private final StarshipRepository starshipRepository;

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public void sendStarshipEvent(String action, Starship starship) {
        StarshipEvent event = new StarshipEvent(action, starship);
        log.info("Sending starship event: {}", event);
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }

    public Page<Starship> getAllStarships(Pageable pageable) {
        return starshipRepository.findAll(pageable);
    }

    @Cacheable(value = "starships", key = "#id")  // Guarda en caché por ID
    public Starship getStarshipById(Long id) {
        log.info("Buscando nave en la base de datos...");
        return starshipRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Starship id " + id + " not found"));
    }

    public Starship createStarship(Starship starship) {
        sendStarshipEvent("CREATED", starship);
        return starshipRepository.save(starship);
    }

    public Starship updateStarship(Long id, Starship starship) {
        if (!starshipRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Starship id " + id + " not found");
        }
        starship.setId(id);
        sendStarshipEvent("UPDATED", starship);
        return starshipRepository.save(starship);
    }

    public void deleteStarship(Long id) {
        if (!starshipRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Starship id " + id + " not found");
        }
        Starship s = getStarshipById(id);
        starshipRepository.deleteById(id);
        sendStarshipEvent("DELETED", s);
    }

    public Page<Starship> getStarshipByName(String name, Pageable pageable) {
        return starshipRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
