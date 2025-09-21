package com.Group_O.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurRequestDto {
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateDeNaissance;
    private String motDePasse;
    private String photo;
    private Integer entrepriseId;
    private AdresseRequestDto adresse;

    // Méthode utilitaire pour convertir en entité
    public static com.Group_O.model.Utilisateur toEntity(UtilisateurRequestDto dto) {
        if (dto == null) return null;
        com.Group_O.model.Utilisateur utilisateur = new com.Group_O.model.Utilisateur();
        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setEmail(dto.getEmail());
        // Conversion LocalDate -> Instant
        if (dto.getDateDeNaissance() != null) {
            utilisateur.setDateDeNaissance(dto.getDateDeNaissance().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        }
        utilisateur.setMotDePasse(dto.getMotDePasse());
        utilisateur.setPhoto(dto.getPhoto());
        utilisateur.setAdresse(dto.getAdresse() != null ? AdresseRequestDto.toEntity(dto.getAdresse()) : null);
        // L'association à l'entreprise se fait dans le service à partir de l'id
        return utilisateur;
    }
} 