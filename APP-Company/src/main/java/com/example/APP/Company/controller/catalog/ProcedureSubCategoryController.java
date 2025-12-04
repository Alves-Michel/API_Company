package com.example.APP.Company.controller.catalog;

import com.example.APP.Company.domain.dto.catalog.ProcedureSubCategoryRegisterDTO;
import com.example.APP.Company.domain.dto.catalog.ProcedureSubCategoryResponseDTO;
import com.example.APP.Company.service.catalog.ProcedureSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/procedureSubCategory")
public class ProcedureSubCategoryController {

    @Autowired
    private ProcedureSubCategoryService procedureSubCategoryService;

    @PostMapping("/register")
    public ResponseEntity<String> registerProcedureSubCategory(@RequestBody ProcedureSubCategoryRegisterDTO body){
        return procedureSubCategoryService.createProcedureSubCategory(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProcedureSubCategoryResponseDTO>> findAllProceduresCategory(){
        var list = procedureSubCategoryService.findAllProceduresCategory();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProcedureSubCategoryResponseDTO>> searchAvailableHours(@RequestParam String name){

        if (name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is null or empty");
        }
        List<ProcedureSubCategoryResponseDTO> list = procedureSubCategoryService.searchProceduresCategory(name);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAvailableHours(@PathVariable("id") UUID id,
                                                     @RequestBody ProcedureSubCategoryRegisterDTO body){
        procedureSubCategoryService.updateProceduryCategory(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAvailableHours(@PathVariable("id")UUID id){
        procedureSubCategoryService.deleteProceduryCategory(id);
        return ResponseEntity.ok().build();
    }
}

