package com.example.APP.Company.domain.dto.catalog;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PricingRegisterDTO (
        UUID procedureId,
        UUID userProfessionalId,
        BigDecimal price,
        BigDecimal discountPercent,
        BigDecimal finalPrice,
        int durationMinutes,
        LocalDateTime validFrom,
        LocalDateTime validTo
){
}
