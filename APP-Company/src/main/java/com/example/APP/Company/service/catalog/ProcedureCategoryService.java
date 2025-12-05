package com.example.APP.Company.service.catalog;

import com.example.APP.Company.domain.dto.catalog.ProcedureCategoryRegisterDTO;
import com.example.APP.Company.domain.dto.catalog.ProcedureCategoryResponseDTO;
import com.example.APP.Company.domain.dto.user.professions.ProfessionsResponseDTO;
import com.example.APP.Company.domain.entity.catalog.category.ProcedureCategory;
import com.example.APP.Company.repository.catalog.ProcedureCategoryRepository;
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
public class ProcedureCategoryService {

    @Autowired
    private ProcedureCategoryRepository procedureCategoryRepository;

    public ResponseEntity createProcedureCategory(ProcedureCategoryRegisterDTO body){
        Optional<ProcedureCategory> category = procedureCategoryRepository.findByNameContainingIgnoreCase(body.name());
        if(category.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This name is already in use");
        }

        ProcedureCategory procedureCategory = new ProcedureCategory();
        procedureCategory.setName(body.name());
        procedureCategory.setActive(body.active());

        this.procedureCategoryRepository.save(procedureCategory);
        return new ResponseEntity( "Create completed successfully",HttpStatus.CREATED);

    }

    public List<ProcedureCategoryResponseDTO> findAllProceduresCategory(){
        return procedureCategoryRepository.findAll().stream()
                .map(procedureCategory -> new ProcedureCategoryResponseDTO(
                        procedureCategory.getId(),
                        procedureCategory.getName(),
                        procedureCategory.isActive()
                )).collect(Collectors.toList());
    }

    public List<ProcedureCategoryResponseDTO> searchProceduresCategory(String search){
        List<ProcedureCategory> categories = procedureCategoryRepository.findByName(search);

        if(categories.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such procedure category exists" + search);
        }

        return categories.stream().map(procedureCategory -> new ProcedureCategoryResponseDTO(
                procedureCategory.getId(),
                procedureCategory.getName(),
                procedureCategory.isActive()
        )).toList();

    }


    public ResponseEntity updateProcedureCategory(UUID id, ProcedureCategoryRegisterDTO body){
        var old = procedureCategoryRepository.findById(id);
        if(old.isPresent()){
            var oldName = old.get();
            Optional.ofNullable(body.name()).ifPresent(oldName::setName);
            Optional.ofNullable(body.active()).ifPresent(oldName::setActive);

            procedureCategoryRepository.save(oldName);
            return new ResponseEntity("Update realized with successfully", HttpStatus.OK);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such procedure category not found");
        }
    }

    public ResponseEntity deleteProcedureCategory(UUID id){
        try {
               var exists = procedureCategoryRepository.existsById(id);
               if(!exists) {
                   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such procedure not found");
               }
               procedureCategoryRepository.deleteById(id);
               return new ResponseEntity("Delete realized with successfully", HttpStatus.OK);
            }catch (NumberFormatException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Id");
        }
    }
}
