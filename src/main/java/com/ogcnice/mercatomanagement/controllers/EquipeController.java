package com.ogcnice.mercatomanagement.controllers;

import com.ogcnice.mercatomanagement.dto.EquipeDto;
import com.ogcnice.mercatomanagement.services.impl.EquipeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/equipes")
@AllArgsConstructor
@Slf4j
public class EquipeController {

    private final EquipeService equipeService;

    /**
     * Cette méthod est un REST endpoint pour enregistrer l'équipe.
     *
     * @param equipe
     * @return résponse entity avec equipe créé
     */
    @PostMapping("/register")
    public ResponseEntity<EquipeDto> createNewEquipe(@Valid @RequestBody EquipeDto equipe) {
        EquipeDto regitredEquipe = equipeService.save(equipe);
        return ResponseEntity.ok(regitredEquipe);
    }

    /**
     * Cette méthode est un REST endpoint pour avoir tous les équipes triées par ordre croissant ou décroissant.
     *
     * @param sort
     * @return résponse entity avec list des équipes triées ou réponse avec le NOT FOUND status
     */
    @GetMapping("/all")
    public ResponseEntity<List<EquipeDto>> findAllSorted(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                                    @RequestParam(defaultValue = "name,asc") String[] sort) {
        List<EquipeDto> equipes = equipeService.findAllSorted(pageNo, pageSize, sort);
        if (Objects.nonNull(equipes)) {
            return ResponseEntity.ok(equipes);
        }
        return ResponseEntity.noContent().build();
    }

}