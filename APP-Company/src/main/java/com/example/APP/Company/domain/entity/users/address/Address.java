package com.example.APP.Company.domain.entity.address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String street;
    private String complement;
    private String neighborhood;
    private String number;
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(length =  2)
    private Uf uf;

    @Pattern(regexp = "\\{4}\\-\\{5}")
    private String cep;
}
