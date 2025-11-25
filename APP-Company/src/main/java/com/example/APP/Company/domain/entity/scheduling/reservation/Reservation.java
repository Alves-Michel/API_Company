package com.example.APP.Company.domain.entity.scheduling.reservation;

import com.example.APP.Company.domain.entity.scheduling.available_hours.AvailableHours;
import com.example.APP.Company.domain.entity.users.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_available_hours", referencedColumnName = "id", nullable = false)
    private AvailableHours availableHours;

    private LocalDateTime created_reservation;

    @Enumerated(EnumType.STRING)
    private Status status;
}
