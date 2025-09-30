package com.groupeO.gestiondestock.dto.auth;

import com.groupeO.gestiondestock.dto.UtilisateurResponseDto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private UtilisateurResponseDto user;
} 