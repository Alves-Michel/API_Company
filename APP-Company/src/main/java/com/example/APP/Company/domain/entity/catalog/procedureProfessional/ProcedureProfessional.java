package com.example.APP.Company.domain.entity.catalog.procedureProfessional;

import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import com.example.APP.Company.domain.entity.catalog.pricing.Pricing;
import com.example.APP.Company.domain.entity.catalog.procedure.Procedure;
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
@Table(name="tb_procedure_professional")
public class ProcedureProfessional {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user_professional", referencedColumnName = "id", nullable = false)
    private UserProfessional userProfessional;

    @ManyToOne
    @JoinColumn(name = "id_procedure", referencedColumnName = "id", nullable = false)
    private Procedure procedure;

    @ManyToOne
    @JoinColumn(name = "id_pricing", referencedColumnName = "id", nullable = false)
    private Pricing pricing;

    @ManyToOne
    @JoinColumn(name = "id_establishment", referencedColumnName = "id", nullable = false)
    private Establishment establishment;


}
