package com.example.APP.Company.domain.entity.users.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name="tb_role")
public class Position {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;
}
