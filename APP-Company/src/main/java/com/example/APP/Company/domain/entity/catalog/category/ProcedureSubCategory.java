package com.example.APP.Company.domain.entity.catalog.category;

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
@Table(name="tb_procedure_subcategory")
public class ProcedureSubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_procedure_category", referencedColumnName = "id")
    private ProcedureCategory procedureCategory;

    private String name;

    private boolean active;
}
