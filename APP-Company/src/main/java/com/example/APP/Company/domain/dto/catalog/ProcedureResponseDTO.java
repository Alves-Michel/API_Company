package com.example.APP.Company.domain.dto.catalog;

import java.util.UUID;

public record ProcedureResponseDTO(
        UUID id,
        String category,
        String subcategory,
        String name,
        String description,
        String image,
        Boolean active) {
}
