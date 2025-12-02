package com.example.APP.Company.service.login;

import com.example.APP.Company.domain.dto.user.login.LoginRequestDTO;
import com.example.APP.Company.domain.dto.user.login.ResponseDTO;
import com.example.APP.Company.domain.entity.users.client.Client;
import com.example.APP.Company.domain.entity.users.user.User;
import com.example.APP.Company.repository.login.AuthUser;
import com.example.APP.Company.service.token.TokenService;
import com.example.APP.Company.repository.users.user.ClientRepository;
import com.example.APP.Company.repository.users.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginAuth {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public ResponseDTO login(LoginRequestDTO body) {

        AuthUser authUser;

        //TENTAR LOGIN DE USER
        Optional<User> userOpt = userRepository.findByUserName(body.login());
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (!passwordEncoder.matches(body.password(), user.getPassword())) {
                throw new RuntimeException("Invalid password!");
            }

            authUser = user;  // LOGIN DE USER OK

        } else {
            // TENTAR LOGIN DE CLIENT
            Optional<Client> clientOpt = clientRepository.findByMail(body.login());
            if (clientOpt.isEmpty()) {
                throw new RuntimeException("User not found!");
            }

            Client client = clientOpt.get();

            if (!passwordEncoder.matches(body.password(), client.getPassword())) {
                throw new RuntimeException("Invalid password!");
            }

            authUser = client;  // LOGIN DE CLIENT OK
        }

        //GERAR TOKEN
        String token = tokenService.generateToken(authUser);

        return new ResponseDTO(authUser.getIdentifier(), token);
    }


}
