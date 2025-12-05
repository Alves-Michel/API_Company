package com.example.APP.Company.repository.catalog;

import com.example.APP.Company.domain.entity.catalog.pricing.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface PricingRepository extends JpaRepository<Pricing, UUID> {

    Optional<Pricing> findByPriceAndDiscountPercentAndProcedure_Id(
            BigDecimal price, BigDecimal discountPercent, UUID procedureId
    );
}
