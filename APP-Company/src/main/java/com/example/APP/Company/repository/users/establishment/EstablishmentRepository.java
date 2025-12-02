package com.example.APP.Company.repository.users.establishment;

import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstablishmentRepository extends JpaRepository<Establishment, UUID> {
    Optional<Establishment> findByCnpj(String cnpj);
    List<Establishment> findByNameContainingIgnoreCase(String name);
}
