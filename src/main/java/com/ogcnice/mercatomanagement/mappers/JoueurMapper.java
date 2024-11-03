package com.ogcnice.mercatomanagement.mappers;

import com.ogcnice.mercatomanagement.dto.JoueurDto;
import com.ogcnice.mercatomanagement.entities.Joueur;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class JoueurMapper {

    /**
     * Convertir vers DTO
     *
     * @param joueur
     * @return DTO de type JoueurDto
     */
    public JoueurDto toDto(Joueur joueur) {
        if (Objects.isNull(joueur)) {
            return null;
        }
        return JoueurDto.builder()
                .name(joueur.getName())
                .position(joueur.getPosition())
                .build();
    }

    /**
     * Convertir vers Entité
     *
     * @param joueurDto
     * @return Entité de type Joueur
     */
    public Joueur toEntity(JoueurDto joueurDto) {
        if (Objects.isNull(joueurDto)) {
            return null;
        }
        return Joueur.builder()
                .name(joueurDto.getName())
                .position(joueurDto.getPosition())
                .build();
    }

    /**
     * Convertir vers list de DTOs
     *
     * @param joueurs
     * @return List de JoueurDto
     */
    public List<JoueurDto> toDtoList(List<Joueur> joueurs) {
        if (Objects.isNull(joueurs)) {
            return null;
        }
        return joueurs.stream().map(joueur -> JoueurDto.builder()
                        .name(joueur.getName())
                        .position(joueur.getPosition())
                        .build())
                .toList();
    }

    /**
     * Convertir vers list d'entités
     *
     * @param joueurDtos
     * @return List de Joueur
     */
    public  List<Joueur> toEntityList(List<JoueurDto> joueurDtos) {
        if (Objects.isNull(joueurDtos)) {
            return null;
        }
        return joueurDtos.stream().map(joueurDto -> Joueur.builder()
                        .name(joueurDto.getName())
                        .position(joueurDto.getPosition())
                        .build())
                .toList();
    }
}
