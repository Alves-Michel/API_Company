package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.user.providers.ProfessionalDTO;
import com.example.APP.Company.domain.dto.user.providers.UserListDTO;
import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import com.example.APP.Company.domain.entity.users.professions.Professions;
import com.example.APP.Company.domain.entity.users.user.Role;
import com.example.APP.Company.domain.entity.users.user.User;
import com.example.APP.Company.domain.dto.user.providers.RegisterRequestDTO;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import com.example.APP.Company.repository.users.establishment.EstablishmentRepository;
import com.example.APP.Company.repository.users.user.ProfessionsRepository;
import com.example.APP.Company.repository.users.user.UserProfessionalRepository;
import com.example.APP.Company.repository.users.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfessionsRepository professionsRepository;

    @Autowired
    EstablishmentRepository  establishmentRepository;

    @Autowired
    UserProfessionalRepository userProfessionalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createUser(@RequestBody RegisterRequestDTO body){
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
            newUser.setGender(body.gender());
            newUser.setRole(body.role());


            User savedUser = userRepository.save(newUser);
            if (body.role() != Role.PROVIDER) {
                return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
            }

            ProfessionalDTO prof = body.professional();
            if(prof == null){
                return new ResponseEntity<>(
                        "Professional data is required when role is PROVIDER",
                        HttpStatus.BAD_REQUEST
                );
            }

            Professions professions = professionsRepository.findById(prof.professionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professional not found"));

            Establishment establishment = establishmentRepository.findById(prof.establishmentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Establishment not found"));

            UserProfessional professional = new UserProfessional();
            professional.setUser(savedUser);
            professional.setProfessionId(professions);
            professional.setEstablishmentId(establishment);
            professional.setBio(prof.bio());
            professional.setImageUrl(prof.imageUrl());
            professional.setCreated_at(LocalDateTime.now());

            userProfessionalRepository.save(professional);

            return new ResponseEntity<>("Professional registered successfully", HttpStatus.CREATED);


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
                        user.getGender()
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
                        user.getGender()
                )).toList();
    }


    public void updateUser(UUID id, RegisterRequestDTO body){
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()){
            var user = userEntity.get();
            Optional.ofNullable(body.name()).ifPresent(user::setName);
            Optional.ofNullable(body.password()).ifPresent(user::setPassword);
            Optional.ofNullable(body.email()).ifPresent(user::setEmail);
            Optional.ofNullable(body.gender()).ifPresent(user::setGender);
            Optional.ofNullable(body.phoneNumber()).ifPresent(user::setPhoneNumber);
            Optional.ofNullable(body.birthDate()).ifPresent(user::setBirthDate);


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
