package com.example.APP.Company.domain.dto.client;

import com.example.APP.Company.domain.entity.users.user.Role;

import java.time.LocalDateTime;

public record ClientRegisterDTO (
        String name,
        String mail,
        String password,
        String phone,
        Role role,
        LocalDateTime created_at

){
}
