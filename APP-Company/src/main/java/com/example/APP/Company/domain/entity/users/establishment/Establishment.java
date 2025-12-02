package com.example.APP.Company.domain.entity.users.establishment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name="tb_establishment")
public class Establishment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")
    private String cnpj;

    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}\\-\\d{4}")
    private String phone;

    @NotBlank
    @Email
    private String mail;

    private String street;
    private String complement;
    private String neighborhood;
    private String number;
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(length =  2)
    private Uf uf;

    @Pattern(regexp = "\\d{5}\\-\\d{3}", message = "CEP invalid")
    private String cep;

    private LocalDateTime created_at;

}
