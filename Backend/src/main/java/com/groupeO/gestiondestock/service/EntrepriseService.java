package com.groupeO.gestiondestock.service;

import com.groupeO.gestiondestock.dto.EntrepriseRequestDto;
import com.groupeO.gestiondestock.dto.EntrepriseResponseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseResponseDto save(EntrepriseRequestDto entrepriseDto);

    EntrepriseResponseDto findById(Integer id);

    EntrepriseResponseDto findByNomEntreprise(String nom);

    List<EntrepriseResponseDto> findAll();

    void delete(Integer id);

    EntrepriseResponseDto update(Integer id, EntrepriseRequestDto entrepriseDto);
}
