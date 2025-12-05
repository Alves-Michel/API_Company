package com.example.APP.Company.controller.catalog;

import com.example.APP.Company.domain.dto.catalog.ProcedureRegisterDTO;
import com.example.APP.Company.domain.dto.catalog.ProcedureResponseDTO;
import com.example.APP.Company.service.catalog.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/procedure")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    @PostMapping("/register")
    public ResponseEntity<String> registerProcedure(@RequestBody ProcedureRegisterDTO body){
        return procedureService.createProcedure(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProcedureResponseDTO>> findAllProcedures(){
        var list = procedureService.findAllProcedures();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProcedureResponseDTO>> searchProcedure(@RequestParam String name){

        if (name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is null or empty");
        }
        List<ProcedureResponseDTO> list = procedureService.searchProcedures(name);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProcedure(@PathVariable("id") UUID id,
                                                     @RequestBody ProcedureRegisterDTO body){
        procedureService.updateProcedure(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProcedure(@PathVariable("id")UUID id){
        procedureService.deleteProcedure(id);
        return ResponseEntity.ok().build();
    }
}

