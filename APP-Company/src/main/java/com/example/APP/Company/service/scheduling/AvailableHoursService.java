package com.example.APP.Company.service.scheduling;

import com.example.APP.Company.domain.dto.scheduling.available_hours.AvailableHoursRegisterDTO;
import com.example.APP.Company.domain.dto.scheduling.available_hours.AvailableHoursResponseDTO;
import com.example.APP.Company.domain.dto.user.professions.ProfessionsResponseDTO;
import com.example.APP.Company.domain.entity.scheduling.available_hours.AvailableHours;
import com.example.APP.Company.domain.entity.users.professions.Professions;
import com.example.APP.Company.domain.entity.users.user.User;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import com.example.APP.Company.repository.scheduling.AvailableHoursRepository;
import com.example.APP.Company.repository.users.user.UserProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class AvailableHoursService {

    @Autowired
    private AvailableHoursRepository availableHoursRepository;

    @Autowired
    private UserProfessionalRepository userProfessionalRepository;

    public ResponseEntity createAvailableHours(AvailableHoursRegisterDTO body){

        UserProfessional userProfessional = userProfessionalRepository.findById(body.userProfessional())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user professional not found"));

        Optional<AvailableHours> exists = availableHoursRepository
                .findByUserProfessional_IdAndWeekdayAndStartHourAndEndHourAndServiceDurationAndBreakDuration(
                        body.userProfessional(),
                        body.weekday(),
                        body.startHour(),
                        body.endHour(),
                        body.serviceDuration(),
                        body.breakDuration()
                );

        if(exists.isPresent()){
            return new ResponseEntity("This availability already exists", HttpStatus.CONFLICT );
        }

        AvailableHours newAvailableHours = new AvailableHours();
        newAvailableHours.setUserProfessional(userProfessional);
        newAvailableHours.setWeekday(body.weekday());
        newAvailableHours.setStartHour(body.startHour());
        newAvailableHours.setEndHour(body.endHour());
        newAvailableHours.setServiceDuration(body.serviceDuration());
        newAvailableHours.setBreakDuration(body.breakDuration());
        newAvailableHours.setStatusHours(body.statusHours());
        availableHoursRepository.save(newAvailableHours);

        this.availableHoursRepository.save(newAvailableHours);
        return new ResponseEntity("Available hours created", HttpStatus.CREATED);
    }

    public List<AvailableHoursResponseDTO> findAllAvailableHours(){
        return availableHoursRepository.findAll().stream()
                .map(availableHours -> new AvailableHoursResponseDTO(
                        availableHours.getId(),
                        availableHours.getUserProfessional().getUser().getName(),
                        availableHours.getUserProfessional().getProfessionId().getName(),
                        availableHours.getUserProfessional().getEstablishmentId().getName(),
                        availableHours.getWeekday(),
                        availableHours.getStartHour(),
                        availableHours.getEndHour(),
                        availableHours.getServiceDuration(),
                        availableHours.getBreakDuration(),
                        availableHours.getStatusHours()

                )).collect(toList());
    }

    public List<AvailableHoursResponseDTO> searchAvailableHours(String name){
        List<AvailableHours> available = availableHoursRepository.findByUserProfessionalId_User_NameContainingIgnoreCase(name);

        if(available.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return available.stream().map(availableHours -> new AvailableHoursResponseDTO(
                availableHours.getId(),
                availableHours.getUserProfessional().getUser().getName(),
                availableHours.getUserProfessional().getProfessionId().getName(),
                availableHours.getUserProfessional().getEstablishmentId().getName(),
                availableHours.getWeekday(),
                availableHours.getStartHour(),
                availableHours.getEndHour(),
                availableHours.getServiceDuration(),
                availableHours.getBreakDuration(),
                availableHours.getStatusHours()
        )).toList();
    }

    public ResponseEntity updateAvailableHours(UUID id, AvailableHoursRegisterDTO body) {
        var existing = availableHoursRepository.findById(id);

        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Available Hours not found");
        }

        var available = existing.get();

        // atualiza profissional
        if (body.userProfessional() != null) {
            UserProfessional professional = userProfessionalRepository.findById(body.userProfessional())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professional not found"));
            available.setUserProfessional(professional);
        }

        Optional.ofNullable(body.weekday()).ifPresent(available::setWeekday);
        Optional.ofNullable(body.startHour()).ifPresent(available::setStartHour);
        Optional.ofNullable(body.endHour()).ifPresent(available::setEndHour);
        Optional.ofNullable(body.serviceDuration()).ifPresent(available::setServiceDuration);
        Optional.ofNullable(body.breakDuration()).ifPresent(available::setBreakDuration);
        Optional.ofNullable(body.statusHours()).ifPresent(available::setStatusHours);

        availableHoursRepository.save(available);

        return new ResponseEntity( "Update completed successfully",HttpStatus.OK);
    }

    public ResponseEntity deleteAvailableHours(UUID id){
        try {
            var exists = availableHoursRepository.existsById(id);
            if(!exists){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Available Hours not found");
            }
            availableHoursRepository.deleteById(id);
            return new ResponseEntity("Delete completed successfully",HttpStatus.OK);

        }catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Id");
        }
    }


}
