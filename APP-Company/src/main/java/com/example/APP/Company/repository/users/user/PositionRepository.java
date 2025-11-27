package com.example.APP.Company.repository.users.user;

import com.example.APP.Company.domain.entity.users.user.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, String> {
    Optional<Position> findByName(String name);
}
