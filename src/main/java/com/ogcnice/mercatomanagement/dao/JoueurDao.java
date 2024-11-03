package com.ogcnice.mercatomanagement.dao;

import com.ogcnice.mercatomanagement.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurDao extends JpaRepository<Joueur, Long> { }
