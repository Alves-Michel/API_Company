package com.example.APP.Company.repository.catalog;

import com.example.APP.Company.domain.entity.catalog.category.ProcedureSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProcedureSubCategoryRepository extends JpaRepository<ProcedureSubCategory, UUID> {
    Optional<ProcedureSubCategory> findByNameContainingIgnoreCase(String name);
    List<ProcedureSubCategory> findByName(String name);
}
