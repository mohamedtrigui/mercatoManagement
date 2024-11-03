package com.ogcnice.mercatomanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity()
@Table(name = "joueurs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Joueur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

}
