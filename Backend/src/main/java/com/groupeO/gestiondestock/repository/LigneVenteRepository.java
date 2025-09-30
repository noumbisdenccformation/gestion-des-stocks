package com.groupeO.gestiondestock.repository;

import com.groupeO.gestiondestock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
    void deleteAllByVenteId(Integer id);
}
