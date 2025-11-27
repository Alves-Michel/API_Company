package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.client.ClientRegisterDTO;
import com.example.APP.Company.domain.dto.client.ClientResponseDTO;
import com.example.APP.Company.domain.entity.users.client.Client;
import com.example.APP.Company.domain.entity.users.user.Role;
import com.example.APP.Company.repository.users.user.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity createdClient(@RequestBody ClientRegisterDTO body){
        Optional<Client> client = this.clientRepository.findByMail(body.mail());
        if(client.isEmpty()){
            Client cli = new Client();
            cli.setName(body.name());
            cli.setMail(body.mail());
            cli.setPassword(passwordEncoder.encode(body.password()));
            cli.setPhone(body.phone());
            cli.setRole(Role.CLIENT);
            cli.setCreated_at(LocalDateTime.now());

            clientRepository.save(cli);
            return new ResponseEntity<>("Client registered successfully", HttpStatus.CREATED);

        }
        return new ResponseEntity<>("Client already exists", HttpStatus.CONFLICT);
    }


    public List<ClientResponseDTO> findAllClients(){
        return this.clientRepository.findAll().stream()
                .map(
                        client -> new ClientResponseDTO(
                              client.getName(),
                              client.getMail(),
                              client.getPhone(),
                              client.getCreated_at()
                        )
                ).collect(Collectors.toList());
    }



}
