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
@Table(name="tb_procedure_category")
public class ProcedureCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String name;
}
