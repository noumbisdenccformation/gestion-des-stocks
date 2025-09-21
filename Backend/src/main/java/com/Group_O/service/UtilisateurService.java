package com.Group_O.service;

import com.Group_O.dto.UtilisateurRequestDto;
import com.Group_O.dto.UtilisateurResponseDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurResponseDto save(UtilisateurRequestDto utilisateurDto);

    UtilisateurResponseDto findById(Integer id);

    UtilisateurResponseDto findByEmailUser(String email);

    List<UtilisateurResponseDto> findAll();

    void delete(Integer id);

    UtilisateurResponseDto update(Integer id, UtilisateurRequestDto utilisateurDto);
}
