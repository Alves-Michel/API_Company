package com.example.APP.Company.service.catalog;

import com.example.APP.Company.domain.dto.catalog.ProcedureSubCategoryRegisterDTO;
import com.example.APP.Company.domain.dto.catalog.ProcedureSubCategoryResponseDTO;
import com.example.APP.Company.domain.entity.catalog.category.ProcedureCategory;
import com.example.APP.Company.domain.entity.catalog.category.ProcedureSubCategory;
import com.example.APP.Company.repository.catalog.ProcedureCategoryRepository;
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
public class ProcedureSubCategoryService {

    @Autowired
    private ProcedureSubCategoryRepository procedureSubCategoryRepository;

    @Autowired
    private ProcedureCategoryRepository procedureCategoryRepository;

    public ResponseEntity createProcedureSubCategory(ProcedureSubCategoryRegisterDTO body){

        ProcedureCategory category = procedureCategoryRepository.findById(body.procedureCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedure Category Not Found"));

        Optional<ProcedureSubCategory> subCategory = procedureSubCategoryRepository.findByNameContainingIgnoreCase(body.name());
        if(subCategory.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This name is already in use");
        }

        ProcedureSubCategory ProcedureSubCategory = new ProcedureSubCategory();

        ProcedureSubCategory.setName(body.name());
        ProcedureSubCategory.setProcedureCategory(category);
        ProcedureSubCategory.setActive(body.active());

        this.procedureSubCategoryRepository.save(ProcedureSubCategory);
        return new ResponseEntity( "Create completed successfully",HttpStatus.CREATED);

    }

    public List<ProcedureSubCategoryResponseDTO> findAllProceduresCategory(){
        return procedureSubCategoryRepository.findAll().stream()
                .map(ProcedureSubCategory -> new ProcedureSubCategoryResponseDTO(
                        ProcedureSubCategory.getId(),
                        ProcedureSubCategory.getProcedureCategory().getName(),
                        ProcedureSubCategory.getName(),
                        ProcedureSubCategory.isActive()
                )).collect(Collectors.toList());
    }

    public List<ProcedureSubCategoryResponseDTO> searchProceduresCategory(String search){
        List<ProcedureSubCategory> categories = procedureSubCategoryRepository.findByName(search);

        if(categories.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such procedure category exists" + search);
        }

        return categories.stream().map(ProcedureSubCategory -> new ProcedureSubCategoryResponseDTO(
                ProcedureSubCategory.getId(),
                ProcedureSubCategory.getProcedureCategory().getName(),
                ProcedureSubCategory.getName(),
                ProcedureSubCategory.isActive()
        )).toList();

    }


    public ResponseEntity updateProceduryCategory(UUID id, ProcedureSubCategoryRegisterDTO body){
        var exists = procedureSubCategoryRepository.findById(id);

        if(exists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedure SubCategory Not Found");
        }

        var subCategory = exists.get();

        if(body.procedureCategory()!=null) {

            ProcedureCategory category = procedureCategoryRepository.findById(body.procedureCategory())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedure Category Not Found"));
            subCategory.setProcedureCategory(category);
        }



            Optional.ofNullable(body.name()).ifPresent(subCategory::setName);
            Optional.ofNullable(body.active()).ifPresent(subCategory::setActive);

            procedureSubCategoryRepository.save(subCategory);
            return new ResponseEntity("Update realized with successfully", HttpStatus.OK);

    }

    public ResponseEntity deleteProceduryCategory(UUID id){
        try {
               var exists = procedureSubCategoryRepository.existsById(id);
               if(!exists) {
                   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such procedure not found");
               }
               procedureSubCategoryRepository.deleteById(id);
               return new ResponseEntity("Delete realized with successfully", HttpStatus.OK);
            }catch (NumberFormatException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Id");
        }
    }
}
