package com.ogcnice.mercatomanagement.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoueurDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String position;
}
