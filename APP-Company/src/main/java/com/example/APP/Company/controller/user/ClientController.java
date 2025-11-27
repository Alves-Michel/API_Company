package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.client.ClientRegisterDTO;
import com.example.APP.Company.domain.dto.client.ClientResponseDTO;
import com.example.APP.Company.service.user.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody ClientRegisterDTO body){
        return clientService.createdClient(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients(){
        var client = clientService.findAllClients();
        return ResponseEntity.ok().body(client);
    }


}
