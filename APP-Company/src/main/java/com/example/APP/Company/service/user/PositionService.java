package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.user.PositionDTO;
import com.example.APP.Company.domain.entity.users.user.Position;
import com.example.APP.Company.repository.users.user.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public ResponseEntity createdRole(@RequestBody PositionDTO body) {
        Optional<Position> role = this.positionRepository.findByName(body.name());

        if (role.isEmpty()) {
            Position newPosition = new Position();
            newPosition.setName(body.name());
            positionRepository.save(newPosition);

            return ResponseEntity.ok().body(newPosition);

        }
        return ResponseEntity.badRequest().build();

    }

    public List<PositionDTO> finAllPositions() {
        return positionRepository.findAll().stream()
                .map(role -> new PositionDTO(
                       role.getId(), role.getName()
                )).collect(Collectors.toList());
    }
}
