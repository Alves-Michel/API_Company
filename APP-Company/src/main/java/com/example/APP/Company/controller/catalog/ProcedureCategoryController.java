package com.example.APP.Company.controller.catalog;

import com.example.APP.Company.domain.dto.catalog.ProcedureCategoryRegisterDTO;
import com.example.APP.Company.domain.dto.catalog.ProcedureCategoryResponseDTO;
import com.example.APP.Company.service.catalog.ProcedureCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/procedureCategory")
public class ProcedureCategoryController {

    @Autowired
    private ProcedureCategoryService procedureCategoryService;

    @PostMapping("/register")
    public ResponseEntity<String> registerProcedureCategory(@RequestBody ProcedureCategoryRegisterDTO body){
        return procedureCategoryService.createProcedureCategory(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProcedureCategoryResponseDTO>> findAllProceduresCategory(){
        var list = procedureCategoryService.findAllProceduresCategory();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProcedureCategoryResponseDTO>> searchAvailableHours(@RequestParam String name){

        if (name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is null or empty");
        }
        List<ProcedureCategoryResponseDTO> list = procedureCategoryService.searchProceduresCategory(name);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAvailableHours(@PathVariable("id") UUID id,
                                                     @RequestBody ProcedureCategoryRegisterDTO body){
        procedureCategoryService.updateProceduryCategory(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAvailableHours(@PathVariable("id")UUID id){
        procedureCategoryService.deleteProceduryCategory(id);
        return ResponseEntity.ok().build();
    }
}

