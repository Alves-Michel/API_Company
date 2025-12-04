package com.example.APP.Company.repository.scheduling;

import com.example.APP.Company.domain.entity.scheduling.available_hours.AvailableHours;
import com.example.APP.Company.domain.entity.scheduling.available_hours.Weekday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvailableHoursRepository extends JpaRepository<AvailableHours, UUID> {
    Optional<AvailableHours>
    findByUserProfessional_IdAndWeekdayAndStartHourAndEndHourAndServiceDurationAndBreakDuration(
            UUID userProfessionalId,
            Weekday weekday,
            LocalTime startHour,
            LocalTime endHour,
            int serviceDuration,
            int breakDuration
    );

    List<AvailableHours> findByUserProfessionalId_User_NameContainingIgnoreCase(String name);
}