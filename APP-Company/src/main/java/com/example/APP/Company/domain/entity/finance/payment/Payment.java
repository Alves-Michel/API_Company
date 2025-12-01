package com.example.APP.Company.domain.entity.finance.payment;

import com.example.APP.Company.domain.entity.scheduling.reservation.Reservation;
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
@Table(name="tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_reservation", referencedColumnName = "id", nullable = false)
    private Reservation reservation;

    private double amount;

    private Channel channel;

    private Method method;

    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

    private String transactionCode;

    private String provider;

    private LocalDateTime createdAt;
}
