package com.example.APP.Company.domain.dto.catalog;

import java.util.UUID;

public record ProcedureRegisterDTO(
        UUID procedureSubCategoryId,
        String name,
        String description,
        String image,
        Boolean active
) {
}
