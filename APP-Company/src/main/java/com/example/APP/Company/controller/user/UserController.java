package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.RegisterRequestDTO;
import com.example.APP.Company.domain.dto.user.UserListDTO;
import com.example.APP.Company.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/search")
    public ResponseEntity<List<UserListDTO>> searchUser(@RequestParam(required = false) String name){

        if(name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name");
        }

        List<UserListDTO> users = userService.searchUser(name);

        return  ResponseEntity.ok().body(users);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") UUID id,
                                           @RequestBody RegisterRequestDTO body){
        userService.updateUser(id, body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
