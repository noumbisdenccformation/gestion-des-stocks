package com.groupeO.gestiondestock.dto;

import com.groupeO.gestiondestock.model.LigneCommandeFournisseur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeFournisseurRequestDto {
    private Integer articleId;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer entrepriseId;

    // Méthode utilitaire pour convertir en entité
    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurRequestDto dto) {
        if (dto == null) return null;
        LigneCommandeFournisseur ligne = new LigneCommandeFournisseur();
        ligne.setQuantite(dto.getQuantite());
        ligne.setPrixUnitaire(dto.getPrixUnitaire());
        ligne.setEntreprise_id(dto.getEntrepriseId());
        // L'association de l'article se fait dans le service à partir de l'id
        return ligne;
    }
} 