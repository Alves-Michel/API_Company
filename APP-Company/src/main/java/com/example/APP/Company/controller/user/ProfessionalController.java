package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.providers.ProfessionalResponseDTO;
import com.example.APP.Company.service.user.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @GetMapping("/list")
    public ResponseEntity<List<ProfessionalResponseDTO>> findAllProfessionals(){
        var profi = professionalService.findAllProfessionals();
        return ResponseEntity.ok().body(profi);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProfessionalResponseDTO>> searchProfessionals(@RequestParam String name){

        if (name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name");
        }

        List<ProfessionalResponseDTO> profi = professionalService.searchProfessionals(name);
        return ResponseEntity.ok().body(profi);
    }
}
