package com.example.APP.Company.repository.catalog;

import com.example.APP.Company.domain.entity.catalog.category.ProcedureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProcedureCategoryRepository extends JpaRepository<ProcedureCategory, UUID> {
    Optional<ProcedureCategory> findByNameContainingIgnoreCase(String name);
    List<ProcedureCategory> findByName(String name);
}
