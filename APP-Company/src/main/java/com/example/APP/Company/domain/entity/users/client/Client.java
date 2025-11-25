package com.example.APP.Company.domain.entity.users.client;

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
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;
    @NotBlank
    private String mail;
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp = "\\(\\{2}\\)\\{4,5}\\-\\{4}")
    private String phone;
    private LocalDateTime created_at;
}
