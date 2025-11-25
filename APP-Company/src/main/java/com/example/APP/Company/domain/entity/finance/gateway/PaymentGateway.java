package com.example.APP.Company.domain.entity.finance.gateway;

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
@Table(name="tb_payment_gateway")
public class PaymentGateway {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String provider;
    private String public_key;
    private String private_key;
    private boolean sandbox;
    private boolean active;

}
