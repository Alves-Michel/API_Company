package com.example.APP.Company.domain.dto.catalog;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PricingResponseDTO (
        UUID id,
    String procedure,
    String professional,
    String establishment,
    BigDecimal price,
    int durationMinutes,
    BigDecimal finalPrice,
    BigDecimal discountPercent,
    LocalDateTime validFrom,
    LocalDateTime validTo,
    LocalDateTime createdAt,
    LocalDateTime updateAt
){}
