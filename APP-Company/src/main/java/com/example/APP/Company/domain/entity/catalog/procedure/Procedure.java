package com.example.APP.Company.domain.entity.catalog.procedure;

import com.example.APP.Company.domain.entity.catalog.category.ProcedureCategory;
import com.example.APP.Company.domain.entity.catalog.category.ProcedureSubCategory;
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
@Table(name="tb_procedure")
public class Procedure {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_subcategory", referencedColumnName = "id", nullable = false)
    private ProcedureSubCategory procedureSubCategory;

    private String name;

    private String description;

    private String image;

    private Boolean active;


}
