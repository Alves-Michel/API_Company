package com.example.APP.Company.service.catalog;

import com.example.APP.Company.domain.dto.catalog.PricingRegisterDTO;
import com.example.APP.Company.domain.dto.catalog.PricingResponseDTO;
import com.example.APP.Company.domain.entity.catalog.pricing.Pricing;
import com.example.APP.Company.domain.entity.catalog.procedure.Procedure;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import com.example.APP.Company.repository.catalog.PricingRepository;
import com.example.APP.Company.repository.catalog.ProcedureRepository;
import com.example.APP.Company.repository.users.user.UserProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
public class PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private UserProfessionalRepository userProfessionalRepository;

    @Autowired
    private ProcedureRepository procedureRepository;


    public ResponseEntity createPricing(PricingRegisterDTO body){

        UserProfessional professional = userProfessionalRepository.findById(body.userProfessionalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professional not found"));

        Procedure procedure = procedureRepository.findById(body.procedureId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedure not found"));

        Optional<Pricing> exists = pricingRepository
                .findByPriceAndDiscountPercentAndProcedure_Id(
                        body.price(),
                        body.discountPercent(),
                        body.procedureId()
                );

        if (exists.isPresent()) {
            return new ResponseEntity("This pricing already exists",HttpStatus.CONFLICT);
        }

        Pricing pricing = new Pricing();

        pricing.setUserProfessional(professional);
        pricing.setProcedure(procedure);
        pricing.setPrice(body.price());
        pricing.setDiscountPercent(body.discountPercent());
        pricing.setFinalPrice(body.finalPrice());
        pricing.setDurationMinutes(body.durationMinutes());
        pricing.setValidFrom(body.validFrom());
        pricing.setValidTo(body.validTo());
        pricing.setCreatedAt(LocalDateTime.now());

        this.pricingRepository.save(pricing);

        return new ResponseEntity("Pricing created", HttpStatus.CREATED);

    }

    public List<PricingResponseDTO> findAllPricing(){
        return pricingRepository.findAll().stream()
                .map(pricing -> new PricingResponseDTO(
                        pricing.getId(),
                        pricing.getProcedure().getName(),
                        pricing.getUserProfessional().getUser().getName(),
                        pricing.getUserProfessional().getEstablishmentId().getName(),
                        pricing.getPrice(),
                        pricing.getDurationMinutes(),
                        pricing.getFinalPrice(),
                        pricing.getDiscountPercent(),
                        pricing.getValidFrom(),
                        pricing.getValidTo(),
                        pricing.getCreatedAt(),
                        pricing.getUpdatedAt()

                )).collect(toList());
    }


}
