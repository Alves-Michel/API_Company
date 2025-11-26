package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.RegisterRequestDTO;
import com.example.APP.Company.domain.dto.user.ResponseDTO;
import com.example.APP.Company.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class User {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegisterRequestDTO body){
        return userService.createUser(body);
    }

}
