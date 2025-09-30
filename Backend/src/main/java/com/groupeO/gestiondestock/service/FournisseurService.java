package com.groupeO.gestiondestock.service;

import com.groupeO.gestiondestock.dto.FournisseurRequestDto;
import com.groupeO.gestiondestock.dto.FournisseurResponseDto;

import java.util.List;

public interface FournisseurService {

    FournisseurResponseDto save(FournisseurRequestDto fournisseurDto);

    FournisseurResponseDto findById(Integer id);

    FournisseurResponseDto findByNomFournisseur(String nom);

    List<FournisseurResponseDto> findAll();

    void delete(Integer id);

    FournisseurResponseDto update(Integer id, FournisseurRequestDto fournisseurDto);
}
