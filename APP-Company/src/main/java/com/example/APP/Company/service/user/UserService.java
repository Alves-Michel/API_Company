package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.user.ResponseDTO;
import com.example.APP.Company.domain.entity.users.user.User;
import com.example.APP.Company.domain.dto.user.RegisterRequestDTO;
import com.example.APP.Company.repository.users.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity createUser(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.userRepository.findByUserName(body.name());
        if(user.isEmpty()){
            User newUser = new User();
            newUser.setName(body.name());
            newUser.setCpf_cnpj(body.cpf_cnpj());
            newUser.getEmail();
            newUser.getUserName();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setPhoneNumber(body.phoneNumber());
            newUser.setGenter(body.genter());
            newUser.setRole(body.role());

            this.userRepository.save(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), newUser.getEmail()));
        }
        return ResponseEntity.notFound().build();
    }
}
