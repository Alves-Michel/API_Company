package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.professions.ProfessionsRegisterDTO;
import com.example.APP.Company.domain.dto.user.professions.ProfessionsResponseDTO;
import com.example.APP.Company.service.user.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/professions")
public class ProfessionsController {

    @Autowired
    private ProfessionService professionService;

    @PostMapping("/register")
    public ResponseEntity<String> registerProfessions(@RequestBody ProfessionsRegisterDTO body){
        return professionService.createProfession(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProfessionsResponseDTO>> findAllProfessions(){
        var profissios = professionService.findAllProfessions();
        return ResponseEntity.ok().body(profissios);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProfessionsResponseDTO>> searchProfessions(@RequestParam String name) {

        if (name == null || name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is null or empty");
        }
        List<ProfessionsResponseDTO> professions = professionService.searchProfessions(name);
        return ResponseEntity.ok().body(professions);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProfessions(@PathVariable("id")UUID id,
                                                  @RequestBody ProfessionsRegisterDTO body){
        professionService.updateProfessions(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProfessions(@PathVariable("id")UUID id){
        professionService.deleteProfessions(id);
        return ResponseEntity.noContent().build();
    }





}
