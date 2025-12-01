package com.example.APP.Company.domain.entity.interaction.message;


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
@Table(name = "tb_message")
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_sender", referencedColumnName = "id", nullable = false)
    private Client sender;

    @ManyToOne
    @JoinColumn(name = "id_receiver", referencedColumnName = "id", nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "id_reservation", referencedColumnName = "id", nullable = false)
    private Reservation reservation;

    private String content;

    private LocalDateTime sendAt;

    private boolean read;


}
