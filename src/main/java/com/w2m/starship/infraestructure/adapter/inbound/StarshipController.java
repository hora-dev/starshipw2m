package com.w2m.starship.infraestructure.adapter.inbound;

import com.w2m.starship.application.service.StarshipServiceImpl;
import com.w2m.starship.domain.model.Starship;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/starships")
@RequiredArgsConstructor
public class StarshipController {

    private final StarshipServiceImpl starShipService;

    @GetMapping
    public ResponseEntity<Page<Starship>> getAllStarships(Pageable pageable) {
        return ResponseEntity.ok(starShipService.getAllStarships(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Starship> getStarshipById(@PathVariable Long id) {
        return ResponseEntity.ok(starShipService.getStarshipById(id));
    }

    @PostMapping
    public ResponseEntity<Starship> createStarship(@RequestBody Starship starship) {
        return ResponseEntity.ok(starShipService.createStarship(starship));
    }

    @PutMapping("{id}")
    public ResponseEntity<Starship> updateStarship(@PathVariable Long id, @RequestBody Starship spacecraft) {
        return ResponseEntity.ok(starShipService.updateStarship(id, spacecraft));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStarship(@PathVariable Long id) {
        starShipService.deleteStarship(id);
        return ResponseEntity.noContent().build();
    }
}