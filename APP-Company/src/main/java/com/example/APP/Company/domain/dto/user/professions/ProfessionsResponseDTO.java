package com.example.APP.Company.domain.dto.user.professions;

import java.util.UUID;

public record ProfessionsResponseDTO(
        UUID id,
        String name
) {
}
