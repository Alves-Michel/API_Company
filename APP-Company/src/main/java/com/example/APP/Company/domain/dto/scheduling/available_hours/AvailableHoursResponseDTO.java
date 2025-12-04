package com.example.APP.Company.domain.dto.scheduling.available_hours;


import com.example.APP.Company.domain.entity.scheduling.available_hours.StatusHours;
import com.example.APP.Company.domain.entity.scheduling.available_hours.Weekday;

import java.time.LocalTime;
import java.util.UUID;

public record AvailableHoursResponseDTO(
        UUID id,
        String nameProfessional,
        String nameProfession,
        String nameEstablishment,
        Weekday weekday,
        LocalTime start_hour,
        LocalTime end_hour,
        int service_duration,
        int break_duration,
        StatusHours statusHours
) {
}
