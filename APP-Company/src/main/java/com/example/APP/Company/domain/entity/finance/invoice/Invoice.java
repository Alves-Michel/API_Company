package com.example.APP.Company.domain.entity.finance.invoice;

import com.example.APP.Company.domain.entity.finance.payment.Payment;
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
@Table(name="tb_invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_payment", referencedColumnName = "id", nullable = false)
    private Payment payment;

    private String invoice_number;

    private LocalDateTime issueDate;

    private double total;

    private String pdf_Url;

    @Enumerated(EnumType.STRING)
    private StatusInvoice statusInvoice;




}
