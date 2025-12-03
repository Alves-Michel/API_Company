package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.user.providers.ProfessionalResponseDTO;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import com.example.APP.Company.repository.users.user.UserProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {

    @Autowired
    UserProfessionalRepository professionalRepository;

    public List<ProfessionalResponseDTO> findAllProfessionals() {
        return professionalRepository.findAll().stream()
                .map(
                        profi -> new ProfessionalResponseDTO(
                               profi.getId(),
                               profi.getUser().getName(),
                               profi.getProfessionId().getName(),
                               profi.getEstablishmentId().getName(),
                               profi.getBio(),
                               profi.getImageUrl()
                        )).collect(Collectors.toList());
    }


    public List<ProfessionalResponseDTO> searchProfessionals(String name){
        List<UserProfessional> profissional =  professionalRepository.findByUser_NameContainingIgnoreCase(name);

        if(profissional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Professional not found" + name);
        }

        return profissional.stream()
                .map(profi -> new ProfessionalResponseDTO(
                        profi.getId(),
                        profi.getUser().getName(),
                        profi.getProfessionId().getName(),
                        profi.getEstablishmentId().getName(),
                        profi.getBio(),
                        profi.getImageUrl()
                )).toList();
    }

}
