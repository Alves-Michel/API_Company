package com.example.APP.Company.domain.entity.scheduling.available_hours;

import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import com.example.APP.Company.domain.entity.users.user_professional.StatusReserve;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_available_hours")
public class AvailableHours {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user_professional",  referencedColumnName = "id", nullable = false)
    private UserProfessional userProfessional;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    private LocalTime startHour;

    private LocalTime endHour;

    private int serviceDuration;

    private int breakDuration;

    @Enumerated(EnumType.STRING)
    private StatusHours  statusHours;


}
