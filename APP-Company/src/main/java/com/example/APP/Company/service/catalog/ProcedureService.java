package com.example.APP.Company.service.catalog;

import com.example.APP.Company.domain.dto.catalog.ProcedureRegisterDTO;
import com.example.APP.Company.domain.dto.catalog.ProcedureResponseDTO;
import com.example.APP.Company.domain.dto.catalog.ProcedureSubCategoryRegisterDTO;
import com.example.APP.Company.domain.dto.catalog.ProcedureSubCategoryResponseDTO;
import com.example.APP.Company.domain.entity.catalog.category.ProcedureCategory;
import com.example.APP.Company.domain.entity.catalog.category.ProcedureSubCategory;
import com.example.APP.Company.domain.entity.catalog.procedure.Procedure;
import com.example.APP.Company.repository.catalog.ProcedureCategoryRepository;
import com.example.APP.Company.repository.catalog.ProcedureRepository;
import com.example.APP.Company.repository.catalog.ProcedureSubCategoryRepository;
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
public class ProcedureService {

    @Autowired
    private ProcedureSubCategoryRepository procedureSubCategoryRepository;

    @Autowired
    private ProcedureRepository procedureRepository;

    public ResponseEntity createProcedure(ProcedureRegisterDTO body){

        ProcedureSubCategory subCategory = procedureSubCategoryRepository.findById(body.procedureSubCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedure Category Not Found"));

        Optional<Procedure> procedure = procedureRepository.findByNameContainingIgnoreCase(body.name());
        if(procedure.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This name is already in use");
        }

        Procedure procedure1 = new Procedure();
        procedure1.setProcedureSubCategory(subCategory);
        procedure1.setName(body.name());
        procedure1.setDescription(body.description());
        procedure1.setImage(body.image());
        procedure1.setActive(body.active());


        this.procedureRepository.save(procedure1);
        return new ResponseEntity( "Create completed successfully",HttpStatus.CREATED);

    }

    public List<ProcedureResponseDTO> findAllProcedures(){
        return procedureRepository.findAll().stream()
                .map(procedure -> new ProcedureResponseDTO(
                        procedure.getId(),
                        procedure.getProcedureSubCategory().getProcedureCategory().getName(),
                        procedure.getProcedureSubCategory().getName(),
                        procedure.getName(),
                        procedure.getDescription(),
                        procedure.getImage(),
                        procedure.getActive()
                )).collect(Collectors.toList());
    }

    public List<ProcedureResponseDTO> searchProcedures(String search){
        List<Procedure> procedures = procedureRepository.findByName(search);

        if(procedures.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such procedure category exists" + search);
        }

        return procedures.stream().map(procedure -> new ProcedureResponseDTO(
                procedure.getId(),
                procedure.getProcedureSubCategory().getProcedureCategory().getName(),
                procedure.getProcedureSubCategory().getName(),
                procedure.getName(),
                procedure.getDescription(),
                procedure.getImage(),
                procedure.getActive()
        )).toList();

    }


    public ResponseEntity updateProcedure(UUID id, ProcedureRegisterDTO body){
        var exists = procedureRepository.findById(id);

        if(exists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedure Not Found");
        }

        var procedure = exists.get();

        if(body.procedureSubCategoryId()!=null) {

            ProcedureSubCategory subCategory = procedureSubCategoryRepository.findById(body.procedureSubCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedure SubCategory Not Found"));
            procedure.setProcedureSubCategory(subCategory);
        }



            Optional.ofNullable(body.name()).ifPresent(procedure::setName);
            Optional.ofNullable(body.description()).ifPresent(procedure::setDescription);
            Optional.ofNullable(body.image()).ifPresent(procedure::setImage);
            Optional.ofNullable(body.active()).ifPresent(procedure::setActive);

            procedureRepository.save(procedure);
            return new ResponseEntity("Update realized with successfully", HttpStatus.OK);

    }

    public ResponseEntity deleteProcedure (UUID id){
        try {
               var exists = procedureRepository.existsById(id);
               if(!exists) {
                   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such procedure not found");
               }
               procedureRepository.deleteById(id);
               return new ResponseEntity("Delete realized with successfully", HttpStatus.OK);
            }catch (NumberFormatException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Id");
        }
    }
}
