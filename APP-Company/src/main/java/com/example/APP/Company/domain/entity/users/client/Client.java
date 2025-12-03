package com.example.APP.Company.domain.entity.users.client;

import com.example.APP.Company.domain.entity.users.user.Role;
import com.example.APP.Company.repository.login.AuthUser;
import jakarta.persistence.*;
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
@Table(name="tb_client")
public class Client implements AuthUser {

    @Override
    public String getIdentifier() {
        return this.mail;
    }

    @Override
    public String getAuthority() {
        return this.role.toString();
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;
    @NotBlank
    private String mail;
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp = "^\\(\\d{2}\\)\\d{4,5}-\\d{4}$")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime created_at;
}
