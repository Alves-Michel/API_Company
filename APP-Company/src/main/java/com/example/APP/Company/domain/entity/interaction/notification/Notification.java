package com.example.APP.Company.domain.entity.interaction.notification;

import com.example.APP.Company.domain.entity.scheduling.reservation.Reservation;
import com.example.APP.Company.domain.entity.users.client.Client;
import com.example.APP.Company.domain.entity.users.user.User;
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
@Table(name = "tb_notification")
public class Notification {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Client client;

    private String message;

    private String title;

    private boolean read;

    private LocalDateTime createdAt;

}
