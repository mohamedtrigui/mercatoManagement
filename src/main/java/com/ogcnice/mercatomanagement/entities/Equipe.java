package com.ogcnice.mercatomanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity()
@Table(name = "equipes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String acronym;

    @Column(nullable = false)
    private BigDecimal budget;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Joueur> joueurs;

}
