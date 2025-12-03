package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.position.PositionResponseDTO;
import com.example.APP.Company.domain.dto.user.position.PositionRequestDTO;
import com.example.APP.Company.service.user.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PostMapping("/register")
    public ResponseEntity<PositionResponseDTO> registerPosition(@RequestBody PositionRequestDTO body){
        var created = positionService.createdPosition(body);
        return ResponseEntity.ok(new PositionResponseDTO(created));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PositionResponseDTO>> findAllPositions(){
        var positions = positionService.finAllPositions();
        return ResponseEntity.ok().body(positions);
    }
}
