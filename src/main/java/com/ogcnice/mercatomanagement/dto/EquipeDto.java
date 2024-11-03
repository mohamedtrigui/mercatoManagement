package com.ogcnice.mercatomanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipeDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private String acronym;

    @NonNull
    private BigDecimal budget;

    private List<JoueurDto> joueurs;
}
