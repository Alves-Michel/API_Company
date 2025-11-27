package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.RegisterRequestDTO;
import com.example.APP.Company.domain.dto.user.UserListDTO;
import com.example.APP.Company.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO body){

       return userService.createUser(body);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserListDTO>> findAllUsers(){
        var users = userService.finAllUser();
        return  ResponseEntity.ok().body(users);
    }

}
