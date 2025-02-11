package com.w2m.starship.infraestructure.adapter.outbound;

import com.w2m.starship.domain.model.Starship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarshipRepository extends JpaRepository<Starship, Long> {
    Page<Starship> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
