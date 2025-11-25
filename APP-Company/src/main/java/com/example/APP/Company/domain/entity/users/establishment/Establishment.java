package com.example.APP.Company.domain.entity.establishment;

import com.example.APP.Company.domain.entity.users.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_establishment")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

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

    private LocalDateTime created_at;

}
