package com.example.APP.Company.domain.dto.user.providers;

import java.util.UUID;

public record ProfessionalDTO(
    UUID professionId,
    UUID establishmentId,
    String bio,
    String imageUrl
) {
}
