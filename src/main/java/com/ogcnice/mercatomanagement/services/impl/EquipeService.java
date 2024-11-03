package com.ogcnice.mercatomanagement.services.impl;

import com.ogcnice.mercatomanagement.dao.EquipeDao;
import com.ogcnice.mercatomanagement.dto.EquipeDto;
import com.ogcnice.mercatomanagement.entities.Equipe;
import com.ogcnice.mercatomanagement.mappers.EquipeMapper;
import com.ogcnice.mercatomanagement.services.IEquipeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EquipeService implements IEquipeService {

    private final EquipeDao equipeDao;

    private final EquipeMapper equipeMapper;

    /**
     * Méthode pour sauvegarder une nouvelle équipe.
     *
     * @param equipe
     * @return équipe créé
     */
    public EquipeDto save(EquipeDto equipe) {
        Equipe newEquipe = this.equipeDao.save(this.equipeMapper.toEntity(equipe));
        return this.equipeMapper.toDto(newEquipe);
    }

    /**
     * Méthode pour trouver tous les équipes triées.
     *
     * @param pageNo
     * @param pageSize
     * @param sort
     * @return list des équipes triées par ordre croissant ou décroissant
     */
    public List<EquipeDto> findAllSorted(Integer pageNo, Integer pageSize, String[] sort) {
        Sort sortBy = getOrders(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortBy);
        Page<Equipe> equipes = equipeDao.findAll(pageable);
        if (Objects.isNull(equipes)) {
            return Collections.emptyList();
        }
        return this.equipeMapper.toDtoList(equipes.toList());
    }

    /**
     * Méthode pour extracter les ordres pour trier la liste des équipes
     *
     * @param sort les paramètres de trie  (par exemple "name,desc")
     * @return Sort Object
     */
    private Sort getOrders(String[] sort) {
        Sort sortBy = Sort.by(Sort.DEFAULT_DIRECTION, "name");
        if (Objects.nonNull(sort) && sort.length == 2) {
           sortBy = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);
        } else if (Objects.nonNull(sort) && sort.length == 1) {
            sortBy = Sort.by(Sort.DEFAULT_DIRECTION, sort[0]);
        }
        return sortBy;
    }
}
