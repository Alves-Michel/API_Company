package com.example.APP.Company.repository.catalog;

import com.example.APP.Company.domain.entity.catalog.category.ProcedureSubCategory;
import com.example.APP.Company.domain.entity.catalog.procedure.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, UUID> {
    Optional<Procedure> findByNameContainingIgnoreCase(String name);
    List<Procedure> findByName(String name);
}
