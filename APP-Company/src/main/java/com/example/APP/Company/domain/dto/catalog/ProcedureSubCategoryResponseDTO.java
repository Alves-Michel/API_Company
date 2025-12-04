package com.example.APP.Company.domain.dto.catalog;

import com.example.APP.Company.domain.entity.catalog.category.ProcedureCategory;

import java.util.UUID;

public record ProcedureSubCategoryResponseDTO (
           UUID id,
           String nameProcedureCategory,
           String name,
           Boolean active
){
}
