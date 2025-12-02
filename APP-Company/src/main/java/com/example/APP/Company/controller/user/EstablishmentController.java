package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.establishment.EstablishmentRegisterDTO;
import com.example.APP.Company.domain.dto.user.establishment.EstablishmentResponseDTO;
import com.example.APP.Company.repository.users.establishment.EstablishmentRepository;
import com.example.APP.Company.service.user.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    @Autowired
    private EstablishmentService establishmentService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEstablishment(@RequestBody EstablishmentRegisterDTO body){

        return establishmentService.createEstablishment(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EstablishmentResponseDTO>> findAllEstablishments(){
        var establishments = establishmentService.findAllEstablishments();
        return ResponseEntity.ok().body(establishments);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EstablishmentResponseDTO>> searchEstablishment(@RequestParam(required = false) String name){
        if(name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name");
        }

        List<EstablishmentResponseDTO> establishments = establishmentService.searchEstablishment(name);

        return ResponseEntity.ok().body(establishments);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateEstablishment(@PathVariable("id") UUID id,
                                                    @RequestBody EstablishmentRegisterDTO body){
        establishmentService.updateEstablishment(id, body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEstablishment(@PathVariable("id") UUID id){
        establishmentService.deleteEstablishment(id);
        return ResponseEntity.noContent().build();
    }
}
