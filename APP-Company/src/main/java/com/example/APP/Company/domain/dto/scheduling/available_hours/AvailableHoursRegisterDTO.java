package com.example.APP.Company.domain.dto.scheduling.available_hours;

import com.example.APP.Company.domain.entity.scheduling.available_hours.StatusHours;
import com.example.APP.Company.domain.entity.scheduling.available_hours.Weekday;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;

import java.time.LocalTime;
import java.util.UUID;

public record AvailableHoursRegisterDTO (
        UUID userProfessional,
        Weekday weekday,
        LocalTime startHour,
        LocalTime endHour,
        int serviceDuration,
        int breakDuration,
        StatusHours statusHours
){
}
