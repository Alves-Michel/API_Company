package com.example.APP.Company.domain.entity.catalog.pricing;

import com.example.APP.Company.domain.entity.catalog.procedure.Procedure;
import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_pricing")
public class Pricing {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_procedure", referencedColumnName = "id", nullable = false)
    private Procedure procedure;

    @ManyToOne
    @JoinColumn(name = "id_user_professional", referencedColumnName = "id", nullable = false)
    private UserProfessional userProfessional;

    private BigDecimal price;

    private int durationMinutes;

    private BigDecimal finalPrice;

    private BigDecimal discountPercent;

    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;





}
