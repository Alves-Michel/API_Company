package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.PositionDTO;
import com.example.APP.Company.service.user.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PostMapping("/register")
    public ResponseEntity<PositionDTO> registerRole(@RequestBody PositionDTO body){
        return positionService.createdRole(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PositionDTO>> findAllRoles(){
        var roles = positionService.finAllRoles();
        return ResponseEntity.ok().body(roles);
    }
}
