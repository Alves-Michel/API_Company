package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.user.UserListDTO;
import com.example.APP.Company.domain.entity.users.user.User;
import com.example.APP.Company.domain.dto.user.RegisterRequestDTO;
import com.example.APP.Company.repository.users.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
            newUser.setEmail(body.email());
            newUser.setUserName(body.userName());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setPhoneNumber(body.phoneNumber());
            newUser.setBirthDate(body.birthDate());
            newUser.setGenter(body.genter());
            newUser.setRole(body.role());
            newUser.setPosition(body.position());

            this.userRepository.save(newUser);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);

        }
        return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }

    public List<UserListDTO> finAllUser() {
        return userRepository.findAll().stream()
                .map(user -> new UserListDTO(
                        user.getId(),
                        user.getName(),
                        user.getCpf_cnpj(),
                        user.getEmail(),
                        user.getUserName(),
                        user.getPhoneNumber(),
                        user.getBirthDate(),
                        user.getGenter(),
                        user.getPosition()
                )).collect(Collectors.toList());
    }

    public List<UserListDTO> searchUser(String name){
        List<User> users = userRepository.findByNameContainingIgnoreCase(name);

        if(users.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found" +  name);
        }

        return users.stream()
                .map(user -> new UserListDTO(
                        user.getId(),
                        user.getName(),
                        user.getCpf_cnpj(),
                        user.getEmail(),
                        user.getUserName(),
                        user.getPhoneNumber(),
                        user.getBirthDate(),
                        user.getGenter(),
                        user.getPosition()
                )).toList();
    }


    public void updateUser(UUID id, RegisterRequestDTO body){
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()){
            var user = userEntity.get();
            Optional.ofNullable(body.name()).ifPresent(user::setName);
            Optional.ofNullable(body.password()).ifPresent(user::setPassword);
            Optional.ofNullable(body.email()).ifPresent(user::setEmail);
            Optional.ofNullable(body.genter()).ifPresent(user::setGenter);
            Optional.ofNullable(body.phoneNumber()).ifPresent(user::setPhoneNumber);
            Optional.ofNullable(body.birthDate()).ifPresent(user::setBirthDate);
            Optional.ofNullable(body.position()).ifPresent(user::setPosition);

            userRepository.save(user);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public void deleteUser(UUID id){
        try {
            var id_user = id;
            var userExists = userRepository.existsById(id_user);
            if (!userExists){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

            }
            userRepository.deleteById(id_user);

        }catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID");
        }
    }
}
