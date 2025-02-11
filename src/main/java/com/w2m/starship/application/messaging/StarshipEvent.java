package com.w2m.starship.application.messaging;

import com.w2m.starship.domain.model.Starship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StarshipEvent {
    private String action;
    private Starship starship;
}
