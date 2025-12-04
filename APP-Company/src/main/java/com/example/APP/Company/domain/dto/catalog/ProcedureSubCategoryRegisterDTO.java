package com.example.APP.Company.domain.dto.catalog;

import java.util.UUID;

public record ProcedureSubCategoryRegisterDTO(
        UUID procedureCategory,
        String name,
        Boolean active
) {
}
