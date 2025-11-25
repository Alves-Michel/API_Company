package com.example.APP.Company.domain.entity.finance.tax;

import com.example.APP.Company.domain.entity.users.address.Uf;
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
@Table(name="tb_tax_rate")
public class TaxRate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(length =  2)
    private Uf uf;

    private  double service_tax;
    private  double commission_tax;
    private LocalDateTime valid_from;
    private LocalDateTime valid_to;


}
