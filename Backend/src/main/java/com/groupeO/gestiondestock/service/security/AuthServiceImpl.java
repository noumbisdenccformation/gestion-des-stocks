package com.groupeO.gestiondestock.service.security;

import com.groupeO.gestiondestock.dto.UtilisateurResponseDto;
import com.groupeO.gestiondestock.dto.AuthResponseDto;
import com.groupeO.gestiondestock.dto.LoginRequestDto;
import com.groupeO.gestiondestock.dto.RegisterRequestDto;
import com.groupeO.gestiondestock.model.Utilisateur;
import com.groupeO.gestiondestock.model.Roles;
import com.groupeO.gestiondestock.model.security.Role;
import com.groupeO.gestiondestock.repository.security.UtilisateurSecurityRepository;
import com.groupeO.gestiondestock.service.AuthService;
import com.groupeO.gestiondestock.repository.RolesRepository;
import com.groupeO.gestiondestock.repository.EntrepriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.groupeO.gestiondestock.exception.BadCredentialsException;
import com.groupeO.gestiondestock.exception.ErrorCodes;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UtilisateurSecurityRepository utilisateurSecurityRepository;
    private final RolesRepository rolesRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {

        // Vérifier si l'utilisateur existe déjà
        if (utilisateurSecurityRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        if (utilisateurSecurityRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Nom d'utilisateur déjà utilisé");
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setUsername(request.getUsername());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getPassword()));
        utilisateur.setEnabled(true);
        utilisateur.setAccountNonExpired(true);
        utilisateur.setCredentialsNonExpired(true);
        utilisateur.setAccountNonLocked(true);
        if (request.getEntrepriseId() != null) {
            utilisateur.setEntreprise(entrepriseRepository.findById(request.getEntrepriseId()).orElse(null));
        }
        utilisateur = utilisateurSecurityRepository.save(utilisateur);
        // Associer le rôle
        Role role = Role.valueOf(request.getRole());
        Roles userRole = new Roles();
        userRole.setRoleName(role);
        userRole.setUtilisateur(utilisateur);
        userRole.setEntreprise_id(request.getEntrepriseId());
        rolesRepository.save(userRole);
        utilisateur.getRoles().add(userRole);
        // Générer le token JWT
        String token = jwtService.generateToken(utilisateur);
        UtilisateurResponseDto userDto = UtilisateurResponseDto.fromEntity(utilisateur);
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setUser(userDto);
        return response;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Identifiants invalides", ErrorCodes.INVALID_CREDENTIALS);
        }

        Utilisateur utilisateur = utilisateurSecurityRepository.findByEmailWithRoles(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        String token = jwtService.generateToken(utilisateur);
        UtilisateurResponseDto userDto = UtilisateurResponseDto.fromEntity(utilisateur);
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setUser(userDto);
        return response;
    }
} 