package dev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.api.entite.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}
