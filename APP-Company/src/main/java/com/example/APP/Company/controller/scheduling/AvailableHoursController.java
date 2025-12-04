package com.example.APP.Company.controller.scheduling;

import com.example.APP.Company.domain.dto.scheduling.available_hours.AvailableHoursRegisterDTO;
import com.example.APP.Company.domain.dto.scheduling.available_hours.AvailableHoursResponseDTO;
import com.example.APP.Company.domain.entity.scheduling.available_hours.AvailableHours;
import com.example.APP.Company.service.scheduling.AvailableHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/availableHours")
public class AvailableHoursController {

    @Autowired
    private AvailableHoursService availableHoursService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAvailableHours(@RequestBody AvailableHoursRegisterDTO body){
        return availableHoursService.createAvailableHours(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AvailableHoursResponseDTO>> findAllAvailableHours(){
        var list = availableHoursService.findAllAvailableHours();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AvailableHoursResponseDTO>> searchAvailableHours(@RequestParam String name){

        if (name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is null or empty");
        }
        List<AvailableHoursResponseDTO> list = availableHoursService.searchAvailableHours(name);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAvailableHours(@PathVariable("id")UUID id,
                                                     @RequestBody AvailableHoursRegisterDTO body){
        availableHoursService.updateAvailableHours(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteAvailableHours(@PathVariable("id")UUID id){
        availableHoursService.deleteAvailableHours(id);
        return ResponseEntity.ok().build();
    }
}
