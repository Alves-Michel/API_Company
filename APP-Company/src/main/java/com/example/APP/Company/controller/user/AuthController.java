package com.example.APP.Company.controller.user;

import com.example.APP.Company.domain.dto.user.LoginRequestDTO;
import com.example.APP.Company.domain.dto.user.ResponseDTO;
import com.example.APP.Company.service.login.LoginAuth;
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

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private  LoginAuth loginAuth;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(body.login(), body.password());

        Authentication authentication = authenticationManager.authenticate(authToken);

        // se chegou aqui, senha está correta
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok("login ok!");
    }
}
