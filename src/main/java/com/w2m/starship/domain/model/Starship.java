package com.w2m.starship.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "starship")
@Data
public class Starship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}