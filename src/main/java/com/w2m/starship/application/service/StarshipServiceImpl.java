package com.w2m.starship.application.service;

import com.w2m.starship.application.port.in.StarshipUseCase;
import com.w2m.starship.domain.model.Starship;
import com.w2m.starship.infraestructure.adapter.outbound.StarshipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class StarshipServiceImpl implements StarshipUseCase {

    private final StarshipRepository starshipRepository;

    @Override
    public Page<Starship> getAllStarships(Pageable pageable) {
        return starshipRepository.findAll(pageable);
    }

    @Override
    public Starship getStarshipById(Long id) {
        return starshipRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Starship id " + id + " not found"));
    }

    @Override
    public Starship createStarship(Starship starship) {
        return starshipRepository.save(starship);
    }

    @Override
    public Starship updateStarship(Long id, Starship starship) {
        if (!starshipRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Starship id " + id + " not found");
        }
        starship.setId(id);
        return starshipRepository.save(starship);
    }

    @Override
    public void deleteStarship(Long id) {
        if (!starshipRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Starship id " + id + " not found");
        }
        starshipRepository.deleteById(id);
    }
}
