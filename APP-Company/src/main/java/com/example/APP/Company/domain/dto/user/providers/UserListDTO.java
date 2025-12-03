package com.example.APP.Company.domain.dto.user.providers;

import com.example.APP.Company.domain.entity.users.user.Gender;

import java.util.Date;
import java.util.UUID;

public record  UserListDTO (
        UUID id,
        String name,
        String cpf_cnpj,
        String email,
        String userName,
        String phoneNumber,
        Date birthDate,
        Gender gender
) {
}
