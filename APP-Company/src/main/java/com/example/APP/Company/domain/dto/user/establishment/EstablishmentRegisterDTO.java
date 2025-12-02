package com.example.APP.Company.domain.dto.user.establishment;


import com.example.APP.Company.domain.entity.users.establishment.Uf;

public record EstablishmentRegisterDTO(
        String name,
        String cnpj,
        String phone,
        String mail,
        String cep,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        Uf uf

) {
}
