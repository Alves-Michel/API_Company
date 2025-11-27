package com.example.APP.Company.repository.users.security;

import com.example.APP.Company.domain.entity.security.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
