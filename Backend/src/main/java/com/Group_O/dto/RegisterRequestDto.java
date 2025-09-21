package com.Group_O.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String role;
    private Integer entrepriseId;
} 