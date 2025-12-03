package com.example.APP.Company.domain.dto.user.providers;

import com.example.APP.Company.domain.entity.users.user.Gender;
import com.example.APP.Company.domain.entity.users.user.Role;

import java.util.Date;

public record RegisterRequestDTO(
        String name,
        String cpf_cnpj,
        String email,
        String userName,
        String password,
        String phoneNumber,
        Date birthDate,
        Gender gender,
        Role role,
        ProfessionalDTO professional

) {
}
