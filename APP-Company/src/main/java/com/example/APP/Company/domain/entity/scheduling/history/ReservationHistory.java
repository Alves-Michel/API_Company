package com.example.APP.Company.domain.entity.scheduling.history;


import com.example.APP.Company.domain.entity.scheduling.reservation.OldStatus;
import com.example.APP.Company.domain.entity.scheduling.reservation.Reservation;
import com.example.APP.Company.domain.entity.scheduling.reservation.Status;
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
@Table(name="tb_reservation_history")
public class ReservationHistory {


    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_reservation", referencedColumnName = "id", nullable = false)
    private Reservation reservation;

    @Enumerated(EnumType.STRING)
    private Status newStatus;

    @Enumerated(EnumType.STRING)
    private OldStatus oldStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}