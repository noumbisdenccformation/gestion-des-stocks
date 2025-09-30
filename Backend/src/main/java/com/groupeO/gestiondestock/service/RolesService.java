package com.groupeO.gestiondestock.service;

import com.groupeO.gestiondestock.dto.RolesRequestDto;
import com.groupeO.gestiondestock.dto.RolesResponseDto;

import java.util.List;

public interface RolesService {
    RolesResponseDto save(RolesRequestDto rolesDto);

    RolesResponseDto findById(Integer id);

    RolesResponseDto findByRoleName(String roleName);

    List<RolesResponseDto> findAll();

    void delete(Integer id);

    RolesResponseDto update(Integer id, RolesRequestDto rolesDto);
}

 
