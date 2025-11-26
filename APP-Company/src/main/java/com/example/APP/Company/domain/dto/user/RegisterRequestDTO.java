package com.example.APP.Company.domain.dto.user;

import com.example.APP.Company.domain.entity.security.role.Role;
import com.example.APP.Company.domain.entity.users.user.Genter;

public record RegisterRequestDTO(
        String name,
        String cpf_cnpj,
        String email,
        String userName,
        String password,
        String phoneNumber,
        Genter genter,
        Role role
) {
}
