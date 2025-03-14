package com.w2m.starship.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Schema(name = "Starship", description = "Starship object saving the id and name of a starship")
@Entity
@Table(name = "starship")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Starship implements Serializable {

    @Schema(description = "The id of the starship")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "The name of the starship")
    @Column(nullable = false, unique = true)
    @NonNull
    private String name;
}