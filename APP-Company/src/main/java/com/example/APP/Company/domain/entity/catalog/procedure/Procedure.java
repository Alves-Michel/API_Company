package com.example.APP.Company.domain.entity.catalog.procedure;

import com.example.APP.Company.domain.entity.catalog.category.ProcedureCategory;
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
@Table(name="tb_reservation")
public class Procedure {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private ProcedureCategory procedureCategory;

    private String name;

    private String image;


}
