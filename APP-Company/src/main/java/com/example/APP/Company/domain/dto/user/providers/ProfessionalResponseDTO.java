package com.example.APP.Company.domain.dto.user.providers;

import java.util.UUID;

public record ProfessionalResponseDTO (
        UUID id,
        String userName,
        String professionName,
        String establishmentName,
        String bio,
        String imageUrl
){}
