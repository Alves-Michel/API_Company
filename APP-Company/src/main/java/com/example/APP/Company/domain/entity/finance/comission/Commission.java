package com.example.APP.Company.domain.entity.finance.comission;


import com.example.APP.Company.domain.entity.finance.payment.Payment;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_commission")
public class Commission {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_professional", referencedColumnName = "id", nullable = false)
    private UserProfessional userProfessional;

    @ManyToOne
    @JoinColumn(name = "id_payment", referencedColumnName = "id", nullable = false)
    private Payment payment;

    private double commission_percentage;

    private double commission_value;

    @Enumerated(EnumType.STRING)
    private StatusCommission statusCommission;

    private LocalDateTime release_date;

    private LocalDateTime payment_date;


}
