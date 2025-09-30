package com.groupeO.gestiondestock.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private UtilisateurResponseDto user;
} 