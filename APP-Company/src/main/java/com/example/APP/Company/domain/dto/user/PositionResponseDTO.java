package com.example.APP.Company.domain.dto.user;

import com.example.APP.Company.domain.entity.users.user.Position;

import java.util.UUID;


public record PositionResponseDTO(UUID Id, String name) {
    public PositionResponseDTO(Position p) {
        this(p.getId(), p.getName());
    }
}
