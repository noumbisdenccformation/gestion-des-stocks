package com.Group_O.service;

import com.Group_O.dto.RolesRequestDto;
import com.Group_O.dto.RolesResponseDto;

import java.util.List;

public interface RolesService {
    RolesResponseDto save(RolesRequestDto rolesDto);

    RolesResponseDto findById(Integer id);

    RolesResponseDto findByRoleName(String roleName);

    List<RolesResponseDto> findAll();

    void delete(Integer id);

    RolesResponseDto update(Integer id, RolesRequestDto rolesDto);
}

 
