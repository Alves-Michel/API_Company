package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.login.LoginRequestDTO;
import com.example.APP.Company.domain.dto.user.login.ResponseDTO;
import com.example.APP.Company.repository.login.AuthUser;
import com.example.APP.Company.service.login.LoginAuth;
import com.example.APP.Company.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
