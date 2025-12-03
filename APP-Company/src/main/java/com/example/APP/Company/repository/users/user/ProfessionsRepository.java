package com.example.APP.Company.repository.users.user;

import com.example.APP.Company.domain.entity.users.professions.Professions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionsRepository extends JpaRepository<Professions, UUID> {
    Optional<Professions> findByName(String name);
    List<Professions> findByNameContainingIgnoreCase(String name);
}
