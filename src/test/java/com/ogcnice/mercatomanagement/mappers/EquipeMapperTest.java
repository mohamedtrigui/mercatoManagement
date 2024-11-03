package com.ogcnice.mercatomanagement.mappers;

import com.ogcnice.mercatomanagement.dto.EquipeDto;
import com.ogcnice.mercatomanagement.dto.JoueurDto;
import com.ogcnice.mercatomanagement.entities.Equipe;
import com.ogcnice.mercatomanagement.entities.Joueur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class EquipeMapperTest {

    @InjectMocks
    private EquipeMapper equipeMapper;

    @Mock
    private JoueurMapper joueurMapper;

    @Test
    void toDto() {
        Equipe equipe = Equipe.builder()
                .name("OGC Nice")
                .acronym("OGCN")
                .joueurs(Collections.emptyList())
                .budget(new BigDecimal(20000000))
                .build();

        EquipeDto equipeDto = this.equipeMapper.toDto(equipe);
        Assertions.assertEquals("OGC Nice", equipeDto.getName());
        Assertions.assertEquals("OGCN", equipeDto.getAcronym());
        Assertions.assertEquals(Collections.emptyList(), equipeDto.getJoueurs());
        Assertions.assertEquals(new BigDecimal(20000000), equipeDto.getBudget());
    }

    @Test
    void toEntity() {
        EquipeDto equipeDto = EquipeDto.builder()
                .name("OGC Nice")
                .acronym("OGCN")
                .joueurs(new ArrayList<>())
                .budget(new BigDecimal(20000000))
                .build();
        Equipe equipe = this.equipeMapper.toEntity(equipeDto);
        Assertions.assertEquals("OGC Nice", equipe.getName());
        Assertions.assertEquals("OGCN", equipe.getAcronym());
        Assertions.assertEquals(List.of(), equipe.getJoueurs());
        Assertions.assertEquals(new BigDecimal(20000000), equipe.getBudget());
    }

    @Test
    void toDtoWhenNull() {
        EquipeDto equipeDto = this.equipeMapper.toDto(null);
        Assertions.assertNull(equipeDto);
    }

    @Test
    void toEntityWhenNull() {
        Equipe equipe = this.equipeMapper.toEntity(null);
        Assertions.assertNull(equipe);
    }
}