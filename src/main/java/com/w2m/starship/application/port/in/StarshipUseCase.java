package com.w2m.starship.application.port.in;

import com.w2m.starship.domain.model.Starship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StarshipUseCase {
    Page<Starship> getAllStarships(Pageable pageable);
    Starship getStarshipById(Long id);
    Starship createStarship(Starship starship);
    Starship updateStarship(Long id, Starship starship);
    void deleteStarship(Long id);
}
