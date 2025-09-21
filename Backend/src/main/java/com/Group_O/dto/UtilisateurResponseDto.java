package com.Group_O.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurResponseDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDeNaissance;
    private String motDePasse;
    private String photo;
    private Integer entrepriseId;
    private EntrepriseResponseDto entreprise;
    private AdresseResponseDto adresse;
    private List<RolesResponseDto> roles;

    // Méthode utilitaire pour convertir une entité en DTO
    public static UtilisateurResponseDto fromEntity(com.loic.gestiondestock.model.Utilisateur utilisateur) {
        if (utilisateur == null) return null;
        return UtilisateurResponseDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .dateDeNaissance(
                    utilisateur.getDateDeNaissance() != null
                        ? utilisateur.getDateDeNaissance().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                        : null
                )
                .motDePasse(utilisateur.getMotDePasse())
                .photo(utilisateur.getPhoto())
                .entrepriseId(utilisateur.getEntreprise() != null ? utilisateur.getEntreprise().getId() : null)
                .entreprise(utilisateur.getEntreprise() != null ? EntrepriseResponseDto.fromEntity(utilisateur.getEntreprise()) : null)
                .adresse(utilisateur.getAdresse() != null ? AdresseResponseDto.fromEntity(utilisateur.getAdresse()) : null)
                .roles(utilisateur.getRoles() != null ? 
                    utilisateur.getRoles().stream()
                        .map(RolesResponseDto::fromEntity)
                        .collect(java.util.stream.Collectors.toList()) : null)
                .build();
    }
} 