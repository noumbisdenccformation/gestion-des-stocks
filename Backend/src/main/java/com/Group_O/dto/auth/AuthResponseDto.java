package com.Group_O.dto.auth;

import com.Group_O.dto.UtilisateurResponseDto;
import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private UtilisateurResponseDto user;
} 