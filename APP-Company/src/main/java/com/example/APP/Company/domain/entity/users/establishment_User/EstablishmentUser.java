package com.example.APP.Company.domain.entity.users.establishment_User;

import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_establishment_user")
public class EstablishmentUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private Establishment establishment;

    private UserProfessional userProfessional;
}

