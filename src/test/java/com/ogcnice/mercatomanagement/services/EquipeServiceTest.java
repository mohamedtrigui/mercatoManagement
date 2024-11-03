package com.ogcnice.mercatomanagement.services;

import com.ogcnice.mercatomanagement.dao.EquipeDao;
import com.ogcnice.mercatomanagement.dto.EquipeDto;
import com.ogcnice.mercatomanagement.entities.Equipe;
import com.ogcnice.mercatomanagement.mappers.EquipeMapper;
import com.ogcnice.mercatomanagement.services.impl.EquipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipeServiceTest {
    @Mock
    private EquipeDao equipeDaoMock;

    @Mock
    private EquipeMapper equipeMapperMock;

    @InjectMocks
    private EquipeService equipeServiceMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.equipeServiceMock = new EquipeService(equipeDaoMock, equipeMapperMock);
    }

    @Test
    void saveTest() {
        Equipe equipeMock = Equipe.builder()
                .id(1L)
                .name("OGC Nice")
                .acronym("OGCN")
                .joueurs(List.of())
                .budget(new BigDecimal(20000000))
                .build();

        EquipeDto equipeDtoMock = EquipeDto.builder()
                .name("OGC Nice")
                .acronym("OGCN")
                .joueurs(List.of())
                .budget(new BigDecimal(20000000))
                .build();


        when(equipeMapperMock.toDto(equipeMock)).thenReturn(equipeDtoMock);
        when(equipeMapperMock.toEntity(equipeDtoMock)).thenReturn(equipeMock);
        when(equipeDaoMock.save(equipeMock)).thenReturn(equipeMock);

        EquipeDto equipe = equipeServiceMock.save(equipeDtoMock);

        Assertions.assertEquals("OGC Nice", equipe.getName());
        Assertions.assertEquals("OGCN", equipe.getAcronym());
        Assertions.assertEquals(new BigDecimal(20000000), equipe.getBudget());
        Assertions.assertEquals(Collections.emptyList(), equipe.getJoueurs());

    }

    @Test
    public void testFindAllSorted_WithValidPaginationAndSorting() {
        // Arrange
        String[] sort = {"name", "desc"};
        Equipe equipe1 = Equipe.builder()
                .id(1L)
                .name("Team A")
                .acronym("TA")
                .joueurs(List.of())
                .budget(new BigDecimal("1000"))
                .build();

        Equipe equipe2 = Equipe.builder()
                .id(1L)
                .name("Team B")
                .acronym("TB")
                .joueurs(List.of())
                .budget(new BigDecimal("2000"))
                .build();

        Page<Equipe> pageResult = new PageImpl<>(List.of(equipe1, equipe2));

        when(equipeDaoMock.findAll(any(Pageable.class))).thenReturn(pageResult);
        when(equipeMapperMock.toDtoList(anyList())).thenReturn(Arrays.asList(
                EquipeDto.builder()
                        .name("Team A")
                        .acronym("TA")
                        .joueurs(List.of())
                        .budget(new BigDecimal("1000"))
                        .build(),
                EquipeDto.builder()
                        .name("Team B")
                        .acronym("TB")
                        .joueurs(List.of())
                        .budget(new BigDecimal("2000"))
                        .build()
        ));

        // Act
        List<EquipeDto> result = equipeServiceMock.findAllSorted(0, 10, sort);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Team A", result.get(0).getName());
        Assertions.assertEquals("TA", result.get(0).getAcronym());

        // Verify sorting and pagination
        verify(equipeDaoMock, times(1)).findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name")));
    }

    @Test
    public void testFindAllSorted_WithEmptyResult() {
        // Arrange
        String[] sort = {"name", "asc"};
        Page<Equipe> emptyPageResult = new PageImpl<>(Collections.emptyList());

        when(equipeDaoMock.findAll(any(Pageable.class))).thenReturn(emptyPageResult);
        when(equipeMapperMock.toDtoList(anyList())).thenReturn(Collections.emptyList());

        // Act
        List<EquipeDto> result = equipeServiceMock.findAllSorted(0, 10, sort);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAllSorted_WithNullSortParameter_DefaultSortingApplied() {
        // Arrange
        String[] sort = null;
        Equipe equipe = Equipe.builder()
                .id(1L)
                .name("Team A")
                .acronym("TA")
                .joueurs(List.of())
                .budget(new BigDecimal("1000"))
                .build();
        Page<Equipe> pageResult = new PageImpl<>(List.of(equipe));

        when(equipeDaoMock.findAll(any(Pageable.class))).thenReturn(pageResult);
        when(equipeMapperMock.toDtoList(anyList())).thenReturn(List.of(EquipeDto.builder()
                .name("Team A")
                .acronym("TA")
                .joueurs(List.of())
                .budget(new BigDecimal("1000"))
                .build()));

        // Act
        List<EquipeDto> result = equipeServiceMock.findAllSorted(0, 10, sort);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Team A", result.get(0).getName());

        // Verify default sort is applied
        verify(equipeDaoMock, times(1)).findAll(PageRequest.of(0, 10, Sort.by(Sort.DEFAULT_DIRECTION, "name")));
    }
}
