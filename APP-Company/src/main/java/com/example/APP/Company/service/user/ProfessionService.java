package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.user.professions.ProfessionsResponseDTO;
import com.example.APP.Company.domain.dto.user.professions.ProfessionsRegisterDTO;
import com.example.APP.Company.domain.entity.users.professions.Professions;
import com.example.APP.Company.repository.users.user.ProfessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfessionService {

    @Autowired
    private ProfessionsRepository professionsRepository;

    public ResponseEntity createProfession(ProfessionsRegisterDTO body){
        Optional<Professions> professions = professionsRepository.findByName(body.name());
        if (professions.isPresent()) {
            throw new RuntimeException("Profession already exists");
        }

        Professions newProfession = new Professions();
        newProfession.setName(body.name());

        this.professionsRepository.save(newProfession);
        return new ResponseEntity("Profession created", HttpStatus.CREATED);
    }

    public List<ProfessionsResponseDTO> findAllProfessions(){
        return professionsRepository.findAll().stream()
                .map(professions -> new ProfessionsResponseDTO(
                        professions.getId(),
                        professions.getName()
                )).collect(Collectors.toList());
    }


    public List<ProfessionsResponseDTO> searchProfessions(String name){
        List<Professions> profi = professionsRepository.findByNameContainingIgnoreCase(name);

        if (profi.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Professions not found" + name);
        }

        return profi.stream().map(profissions -> new ProfessionsResponseDTO(
                profissions.getId(),
                profissions.getName()
        )).toList();

    }

    public void updateProfessions(UUID id, ProfessionsRegisterDTO body){
        var  profissions = professionsRepository.findById(id);
        if (profissions.isPresent()){
            var profi = profissions.get();
            Optional.ofNullable(body.name()).ifPresent(profi::setName);

            professionsRepository.save(profi);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Professions not found" );
        }
    }

    public void deleteProfessions(UUID id){
        try {
            var id_profi = id;
            var profiExists = professionsRepository.existsById(id_profi);
            if (!profiExists){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Professions not found");
            }

            professionsRepository.deleteById(id_profi);
        }catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Id");
        }
    }


}
