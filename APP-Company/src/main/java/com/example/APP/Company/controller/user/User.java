package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.RegisterRequestDTO;
import com.example.APP.Company.domain.dto.user.ResponseDTO;
import com.example.APP.Company.domain.dto.user.UserListDTO;
import com.example.APP.Company.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class User {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO body){
       userService.createUser(body);
       return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserListDTO>> findAllUsers(){
        var users = userService.finAllUser();
        return  ResponseEntity.ok().body(users);
    }

}
