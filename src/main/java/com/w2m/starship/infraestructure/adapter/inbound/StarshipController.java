package com.w2m.starship.infraestructure.adapter.inbound;

import com.w2m.starship.application.service.StarshipServiceImpl;
import com.w2m.starship.domain.model.Starship;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Starships API", description = "CRUD operations related to starships")
@RestController
@RequestMapping("/starships")
@RequiredArgsConstructor
public class StarshipController {

    private final StarshipServiceImpl starShipService;

    @Operation(summary = "Get all starships available")
    @GetMapping
    public ResponseEntity<Page<Starship>> getAllStarships(Pageable pageable) {
        return ResponseEntity.ok(starShipService.getAllStarships(pageable));
    }

    @Operation(summary = "Get a starship searching by its id")
    @GetMapping("{id}")
    public ResponseEntity<Starship> getStarshipById(@PathVariable Long id) {
        return ResponseEntity.ok(starShipService.getStarshipById(id));
    }

    @Operation(summary = "Get all starships containg a text on their name ")
    @GetMapping("/search/name")
    public ResponseEntity<Page<Starship>> getStarshipByName(@RequestParam String text, Pageable pageable) {
        return ResponseEntity.ok(starShipService.getStarshipByName(text, pageable));
    }


    @Operation(summary = "Create a starship and save it on db")
    @PostMapping
    public ResponseEntity<Starship> createStarship(@RequestBody String starshipName) {
        return ResponseEntity.ok(starShipService.createStarship(starshipName));
    }

    @Operation(summary = "Update a starship searching by its id")
    @PutMapping("{id}")
    public ResponseEntity<Starship> updateStarship(@PathVariable Long id, @RequestBody String starshipName) {
        return ResponseEntity.ok(starShipService.updateStarship(id, starshipName));
    }

    @Operation(summary = "Delete a starship searching by its id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStarship(@PathVariable Long id) {
        starShipService.deleteStarship(id);
        return ResponseEntity.noContent().build();
    }
}