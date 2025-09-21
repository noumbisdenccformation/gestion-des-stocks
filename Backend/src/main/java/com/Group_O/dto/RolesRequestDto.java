package com.Group_O.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesRequestDto {
    @JsonProperty("roleName")
    private String roleName;
    
    @JsonProperty("utilisateurId")
    private Integer utilisateurId;
    
    @JsonProperty("entrepriseId")
    private Integer entrepriseId;

    // Méthode utilitaire pour convertir en entité
    public static com.Group_O.model.Roles toEntity(RolesRequestDto dto) {
        if (dto == null) return null;
        com.Group_O.model.Roles roles = new com.Group_O.model.Roles();
        
        // Gestion sécurisée de la conversion enum
        if (dto.getRoleName() != null && !dto.getRoleName().trim().isEmpty()) {
            try {
                roles.setRoleName(com.Group_O.model.security.Role.valueOf(dto.getRoleName().trim().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Valeur de rôle invalide: " + dto.getRoleName() + 
                    ". Valeurs acceptées: ADMIN, MANAGER, USER, VENDEUR, STOCKISTE, ENTREPRISE");
            }
        }
        
        roles.setEntreprise_id(dto.getEntrepriseId());
        // L'association à l'utilisateur se fait dans le service à partir de l'id
        return roles;
    }
} 