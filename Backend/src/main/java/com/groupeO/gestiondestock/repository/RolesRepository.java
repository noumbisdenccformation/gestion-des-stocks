package com.groupeO.gestiondestock.repository;

import com.groupeO.gestiondestock.model.Roles;
import com.groupeO.gestiondestock.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(Role roleName);
}
