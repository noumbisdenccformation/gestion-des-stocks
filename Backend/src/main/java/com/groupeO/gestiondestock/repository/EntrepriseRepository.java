package com.groupeO.gestiondestock.repository;

import com.groupeO.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    Optional<Entreprise> findByNomEntreprise(String nomEntreprise);
}
