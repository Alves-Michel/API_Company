package com.example.APP.Company.service.login;

import com.example.APP.Company.domain.dto.user.LoginRequestDTO;
import com.example.APP.Company.domain.dto.user.ResponseDTO;
import com.example.APP.Company.domain.entity.users.user.User;
import com.example.APP.Company.infra.token.TokenService;
import com.example.APP.Company.repository.users.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginAuth {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public ResponseDTO login(LoginRequestDTO body){

        User user = userRepository.findByUserName(body.username()).orElseThrow(()
        -> new RuntimeException("Username not found!"));
        if (!passwordEncoder.matches(body.password(), user.getPassword())){
            throw new RuntimeException("Passwords don't match!");
        }

        String token = tokenService.generateToken(user);

        return new ResponseDTO(user.getName(), token);

    }
}
