package com.w2m.starship.application.port.in;

import com.w2m.starship.domain.model.Starship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StarshipUseCase {
    Page<Starship> getAllStarships(Pageable pageable);
    Starship getStarshipById(Long id);
    Starship createStarship(String starshipName);
    Starship updateStarship(Long id, String starshipName);
    void deleteStarship(Long id);
}
