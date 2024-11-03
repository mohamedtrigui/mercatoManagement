package com.ogcnice.mercatomanagement.mappers;

import com.ogcnice.mercatomanagement.dto.EquipeDto;
import com.ogcnice.mercatomanagement.entities.Equipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class EquipeMapper {
    private final JoueurMapper joueurMapper;


    /**
     * Convertir vers DTO
     *
     * @param equipe
     * @return DTO de type EquipeDto
     */
    public EquipeDto toDto(Equipe equipe) {
        if (Objects.isNull(equipe)) {
            return null;
        }
        return EquipeDto.builder()
                .name(equipe.getName())
                .acronym(equipe.getAcronym())
                .budget(equipe.getBudget())
                .joueurs(this.joueurMapper.toDtoList(equipe.getJoueurs()))
                .build();
    }

    /**
     * Convertir vers Entité
     *
     * @param equipeDto
     * @return Entité de type Equipe
     */
    public Equipe toEntity(EquipeDto equipeDto) {
        if (Objects.isNull(equipeDto)) {
            return null;
        }
        return Equipe.builder()
                .name(equipeDto.getName())
                .acronym(equipeDto.getAcronym())
                .budget(equipeDto.getBudget())
                .joueurs(this.joueurMapper.toEntityList(equipeDto.getJoueurs()))
                .build();
    }

    /**
     * Convertir vers list de DTOs
     *
     * @param equipes
     * @return List de EquipeDto
     */
    public List<EquipeDto> toDtoList(List<Equipe> equipes) {
        if (Objects.isNull(equipes)) {
            return null;
        }
        return equipes.stream().map(equipe -> EquipeDto.builder()
                .name(equipe.getName())
                .acronym(equipe.getAcronym())
                .budget(equipe.getBudget())
                .joueurs(this.joueurMapper.toDtoList(equipe.getJoueurs()))
                .build())
                .toList();
    }

    /**
     * Convertir vers list d'entités
     *
     * @param equipeDtos
     * @return List de Equipe
     */
    public  List<Equipe> toEntityList(List<EquipeDto> equipeDtos) {
        if (Objects.isNull(equipeDtos)) {
            return null;
        }
        return equipeDtos.stream().map(equipeDto -> Equipe.builder()
                .name(equipeDto.getName())
                .acronym(equipeDto.getAcronym())
                .budget(equipeDto.getBudget())
                .joueurs(this.joueurMapper.toEntityList(equipeDto.getJoueurs()))
                .build())
                .toList();
    }
}
