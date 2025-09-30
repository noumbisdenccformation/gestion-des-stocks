package com.groupeO.gestiondestock.service;

import com.groupeO.gestiondestock.dto.CategorieRequestDto;
import com.groupeO.gestiondestock.dto.CategorieResponseDto;

import java.util.List;

public interface CategorieService {

    CategorieResponseDto save(CategorieRequestDto categorieDto);

    CategorieResponseDto findById(Integer id);

    CategorieResponseDto findByCodeCategorie(String code);

    List<CategorieResponseDto> findAll();

    void delete(Integer id);

    CategorieResponseDto update(Integer id, CategorieRequestDto categorieDto);

}
