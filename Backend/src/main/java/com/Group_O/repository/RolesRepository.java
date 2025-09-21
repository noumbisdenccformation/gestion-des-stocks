package com.Group_O.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Group_O.model.Roles;
import com.Group_O.model.security.Role;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(Role roleName);
}
