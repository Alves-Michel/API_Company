package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.LoginRequestDTO;
import com.example.APP.Company.domain.dto.user.ResponseDTO;
import com.example.APP.Company.service.login.LoginAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private  LoginAuth loginAuth;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body){
        ResponseDTO responseDTO = loginAuth.login(body);
        return ResponseEntity.ok(responseDTO);
    }
}
