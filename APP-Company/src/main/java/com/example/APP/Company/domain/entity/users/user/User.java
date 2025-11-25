package com.example.APP.Company.domain.entity.user;

import com.example.APP.Company.domain.entity.security.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_user")

public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    private Role role;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    private String cpf_cnpj;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}\\-\\d{4}")
    private String phoneNumber;

    @NotBlank
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Genter genter;


    private LocalDateTime created_at;
    private LocalDateTime updated_at;



}
