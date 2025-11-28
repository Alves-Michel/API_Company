package com.example.APP.Company.domain.dto.client;

import java.time.LocalDateTime;

public record ClientResponseDTO(
        String name,
        String mail,
        String phone,
        LocalDateTime created_at
){
}
