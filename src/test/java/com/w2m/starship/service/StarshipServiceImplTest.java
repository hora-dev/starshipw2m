package com.w2m.starship.service;

import com.w2m.starship.application.messaging.StarshipEvent;
import com.w2m.starship.application.service.StarshipServiceImpl;
import com.w2m.starship.domain.model.Starship;
import com.w2m.starship.infraestructure.adapter.outbound.StarshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class StarshipServiceImplTest {

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private StarshipRepository starshipRepository;

    @Autowired
    private StarshipServiceImpl starshipService;

    private Starship starship;

    @BeforeEach
    void setUp() {
        starship = new Starship();
        starship.setId(1L);
        starship.setName("Millennium Falcon");
    }

    @Test
    void getAllStarships_ShouldReturnPagedResults() {
        Pageable pageable = PageRequest.of(0, 10);
        when(starshipRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(starship)));

        Page<Starship> result = starshipService.getAllStarships(pageable);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        verify(starshipRepository).findAll(pageable);
    }

    @Test
    void getStarshipById_WhenExists_ShouldReturnStarship() {
        when(starshipRepository.findById(1L)).thenReturn(Optional.of(starship));

        Starship result = starshipService.getStarshipById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(starshipRepository).findById(1L);
    }

    @Test
    void getStarshipById_WhenNotFound_ShouldThrowException() {
        when(starshipRepository.findById(2L)).thenReturn(Optional.empty());

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> starshipService.getStarshipById(2L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(starshipRepository).findById(2L);
    }

    @Test
    void createStarship_ShouldSaveAndReturnStarship() {
        when(starshipRepository.save(any(Starship.class))).thenReturn(starship);

        Starship result = starshipService.createStarship(starship.getName());

        assertNotNull(result);
        assertEquals("Millennium Falcon", result.getName());
        verify(starshipRepository).save(any(Starship.class));
        verify(rabbitTemplate).convertAndSend(eq("starship.exchange"), eq("starship.routing.key"), any(StarshipEvent.class));

    }

    @Test
    void updateStarship_WhenExists_ShouldUpdateAndReturnStarship() {
        when(starshipRepository.findById(1L)).thenReturn(Optional.of(starship));
        when(starshipRepository.save(starship)).thenReturn(starship);

        Starship result = starshipService.updateStarship(1L, "starship new name");

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("starship new name", result.getName());
        verify(starshipRepository).findById(1L);
        verify(starshipRepository).save(starship);
    }

    @Test
    void updateStarship_WhenNotFound_ShouldThrowException() {
        when(starshipRepository.findById(2L)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Starship id " + 2L + " not found"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> starshipService.updateStarship(2L, starship.getName()));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(starshipRepository).findById(2L);
    }

    @Test
    void deleteStarship_WhenExists_ShouldDelete() {
        when(starshipRepository.findById(1L)).thenReturn(Optional.of(starship));

        doNothing().when(starshipRepository).deleteById(1L);

        assertDoesNotThrow(() -> starshipService.deleteStarship(1L));
        verify(starshipRepository).findById(1L);
        verify(starshipRepository).deleteById(1L);
    }

    @Test
    void deleteStarship_WhenNotFound_ShouldThrowException() {
        when(starshipRepository.findById(2L)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Starship id " + 2L + " not found"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> starshipService.deleteStarship(2L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(starshipRepository).findById(2L);
    }

    @Test
    void getStarshipByName_ShouldReturnPagedResults() {
        Pageable pageable = PageRequest.of(0, 10);
        when(starshipRepository.findByNameContainingIgnoreCase("Falcon", pageable))
                .thenReturn(new PageImpl<>(List.of(starship)));

        Page<Starship> result = starshipService.getStarshipByName("Falcon", pageable);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        verify(starshipRepository).findByNameContainingIgnoreCase("Falcon", pageable);
    }
}
