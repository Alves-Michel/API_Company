package com.example.APP.Company.domain.entity.finance.statement;

import com.example.APP.Company.domain.entity.users.establishment.Establishment;
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
public class FinancialStatement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_establishment",referencedColumnName = "id", nullable = false)
    private Establishment establishment;

    private double total_income;
    private double total_expenses;
    private double balance;
    private LocalDateTime reference_date;

}
