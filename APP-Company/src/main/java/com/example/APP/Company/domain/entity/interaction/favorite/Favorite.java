package com.example.APP.Company.domain.entity.interaction.favorite;

import com.example.APP.Company.domain.entity.users.client.Client;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
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
@Table(name="tb_financial_statement")
public class Favorite {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_user_professional", referencedColumnName = "id", nullable = false)
    private UserProfessional userProfessional;

    private LocalDateTime createdAt;




}
