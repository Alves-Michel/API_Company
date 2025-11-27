package com.example.APP.Company.domain.dto.user;

import com.example.APP.Company.domain.entity.security.role.Role;
import com.example.APP.Company.domain.entity.users.user.Genter;

import java.util.Date;

public record  UserListDTO (
        String name,
        String cpf_cnpj,
        String email,
        String userName,
        String phoneNumber,
        Date birthDate,
        Genter genter,
        Role role
) {
}
