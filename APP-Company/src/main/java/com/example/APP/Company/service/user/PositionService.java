package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.user.position.PositionResponseDTO;
import com.example.APP.Company.domain.dto.user.position.PositionRequestDTO;
import com.example.APP.Company.domain.entity.users.user.Position;
import com.example.APP.Company.repository.users.user.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Position createdRole(PositionRequestDTO body) {
        Optional<Position> role = positionRepository.findByName(body.name());

        if (role.isPresent()) {
            throw new RuntimeException("Position already exists");
        }

        Position newPosition = new Position();
        newPosition.setName(body.name());
        return positionRepository.save(newPosition);
    }

    public List<PositionResponseDTO> finAllPositions() {
        return positionRepository.findAll().stream()
                .map(role -> new PositionResponseDTO(
                       role.getId(), role.getName()
                )).collect(Collectors.toList());
    }
}
