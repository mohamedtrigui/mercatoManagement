package com.ogcnice.mercatomanagement.services;

import com.ogcnice.mercatomanagement.dto.EquipeDto;

import java.util.List;

public interface IEquipeService {
    EquipeDto save(EquipeDto equipe);
    List<EquipeDto> findAllSorted(Integer pageNo, Integer pageSize, String[] field);

}
