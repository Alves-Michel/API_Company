package com.example.APP.Company.service.user;

import com.example.APP.Company.domain.dto.user.establishment.EstablishmentRegisterDTO;
import com.example.APP.Company.domain.dto.user.establishment.EstablishmentResponseDTO;
import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import com.example.APP.Company.repository.users.establishment.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;

    public ResponseEntity createEstablishment(@RequestBody EstablishmentRegisterDTO body) {
        Optional<Establishment> establishment = establishmentRepository.findByCnpj(body.cnpj());
        if (establishment.isEmpty()) {
            Establishment newEst = new Establishment();
            newEst.setName(body.name());
            newEst.setCnpj(body.cnpj());
            newEst.setPhone(body.phone());
            newEst.setMail(body.mail());
            newEst.setCep(body.cep());
            newEst.setStreet(body.street());
            newEst.setNumber(body.number());
            newEst.setComplement(body.complement());
            newEst.setNeighborhood(body.neighborhood());
            newEst.setCity(body.city());
            newEst.setUf(body.uf());

            this.establishmentRepository.save(newEst);
            return new ResponseEntity<>("Establishment created successfully", HttpStatus.CREATED);

        }

        return new ResponseEntity<>("Establishment already exists", HttpStatus.CONFLICT);
    }

    public List<EstablishmentResponseDTO> findAllEstablishments() {
        return establishmentRepository.findAll().stream()
                .map(est -> new EstablishmentResponseDTO(
                        est.getId(),
                        est.getName(),
                        est.getCnpj(),
                        est.getPhone(),
                        est.getMail(),
                        est.getCep(),
                        est.getStreet(),
                        est.getNumber(),
                        est.getComplement(),
                        est.getNeighborhood(),
                        est.getCity(),
                        est.getUf()
                )).collect(Collectors.toList());
    }

    public List<EstablishmentResponseDTO> searchEstablishment(String name){
        List<Establishment> establishments = establishmentRepository.findByNameContainingIgnoreCase(name);

        if(establishments.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Establishment not found" + name);
        }

        return establishments.stream()
                .map(est -> new EstablishmentResponseDTO(
                        est.getId(),
                        est.getName(),
                        est.getCnpj(),
                        est.getPhone(),
                        est.getMail(),
                        est.getCep(),
                        est.getStreet(),
                        est.getNumber(),
                        est.getComplement(),
                        est.getNeighborhood(),
                        est.getCity(),
                        est.getUf()
                )).toList();

    }


    public void updateEstablishment(UUID id, EstablishmentRegisterDTO body) {
        var establishmentEntity = establishmentRepository.findById(id);

        if (establishmentEntity.isPresent()) {
            var est =  establishmentEntity.get();
            Optional.ofNullable(body.name()).ifPresent(est::setName);
            Optional.ofNullable(body.cnpj()).ifPresent(est::setCnpj);
            Optional.ofNullable(body.phone()).ifPresent(est::setPhone);
            Optional.ofNullable(body.mail()).ifPresent(est::setMail);
            Optional.ofNullable(body.cep()).ifPresent(est::setCep);
            Optional.ofNullable(body.street()).ifPresent(est::setStreet);
            Optional.ofNullable(body.number()).ifPresent(est::setNumber);
            Optional.ofNullable(body.complement()).ifPresent(est::setComplement);
            Optional.ofNullable(body.neighborhood()).ifPresent(est::setNeighborhood);
            Optional.ofNullable(body.city()).ifPresent(est::setCity);
            Optional.ofNullable(body.uf()).ifPresent(est::setUf);

            this.establishmentRepository.save(est);

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Establishment not found");
        }
    }

    public void deleteEstablishment(UUID id) {
        try {
            var id_est = id;
            var exists = establishmentRepository.existsById(id_est);
            if (!exists) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Establishment not found");
            }
            establishmentRepository.deleteById(id_est);
        }catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID");
        }
    }

}
