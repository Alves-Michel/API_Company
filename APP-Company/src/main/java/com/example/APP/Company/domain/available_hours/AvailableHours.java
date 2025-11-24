package com.example.APP.Company.domain.available_hours;

import com.example.APP.Company.domain.establishment.Establishment;
import com.example.APP.Company.domain.user_professional.StatusReserve;
import com.example.APP.Company.domain.user_professional.UserProfessional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_establishment", referencedColumnName = "id", nullable = false)
    private Establishment establishment;

    @ManyToOne
    @JoinColumn(name = "id_user_professional",  referencedColumnName = "id", nullable = false)
    private UserProfessional userProfessional;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private StatusReserve status;

}
