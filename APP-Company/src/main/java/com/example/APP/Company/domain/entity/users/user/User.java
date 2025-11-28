package com.example.APP.Company.domain.entity.users.user;

import com.example.APP.Company.repository.login.AuthUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_user")

public class User implements AuthUser {



    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_position", referencedColumnName = "id", nullable = false)
    private Position position;

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


    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Genter genter;

    @Enumerated(EnumType.STRING)
    private Role role;

    //private LocalDateTime created_at;
    //private LocalDateTime updated_at;


    @Override
    public String getIdentifier() {
        return this.userName;
    }

    @Override
    public String getAuthority() {
        return this.role.toString();
    }


}
