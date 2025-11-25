package com.example.APP.Company.domain.entity.users.establishment_User;

import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_establishment_user")
public class EstablishmentUser {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_establishment", referencedColumnName = "id", nullable = false)
    private Establishment establishment;

    @ManyToOne
    @JoinColumn(name = "id_user_professional", referencedColumnName = "id", nullable = false)
    private UserProfessional userProfessional;
}

