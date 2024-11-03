package com.ogcnice.mercatomanagement.controllers;

import com.ogcnice.mercatomanagement.dto.EquipeDto;
import com.ogcnice.mercatomanagement.services.impl.EquipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipeControllerTest {
    @InjectMocks
    private EquipeController equipeControllerMock;

    @Mock
    private EquipeService equipeServiceMock;

    @Test
    void createNewEquipeTest() {
        EquipeDto equipeMock = EquipeDto.builder()
                .name("OGC Nice")
                .acronym("OGCN")
                .joueurs(new ArrayList<>())
                .budget(new BigDecimal(20000000))
                .build();

        when(equipeServiceMock.save(equipeMock)).thenReturn(equipeMock);
        ResponseEntity<EquipeDto> response = equipeControllerMock.createNewEquipe(equipeMock);

        Assertions.assertEquals(equipeMock, response.getBody());
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void findAllSortedTest() {
        List<EquipeDto> equipeMock = List.of(
                EquipeDto.builder()
                .name("OGC Nice")
                .acronym("OGCN")
                .joueurs(new ArrayList<>())
                .budget(new BigDecimal(20000000))
                .build());

        when(equipeServiceMock.findAllSorted(0,5, new String[]{"name", "asc"})).thenReturn(equipeMock);

        ResponseEntity<List<EquipeDto>> response = equipeControllerMock.findAllSorted(0,5, new String[]{"name", "asc"});

        Assertions.assertEquals(equipeMock, response.getBody());
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

}
