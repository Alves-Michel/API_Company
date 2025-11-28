package com.example.APP.Company.domain.dto.user;

import com.example.APP.Company.domain.entity.users.user.Position;
import com.example.APP.Company.domain.entity.users.user.Genter;
import com.example.APP.Company.domain.entity.users.user.Role;

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
        Genter genter,
        Position position
) {
}
