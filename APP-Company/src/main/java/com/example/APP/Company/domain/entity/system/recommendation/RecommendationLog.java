package com.example.APP.Company.domain.entity.system.recommendation;

import com.example.APP.Company.domain.entity.catalog.procedure.Procedure;
import com.example.APP.Company.domain.entity.users.client.Client;
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
@Table(name="tb_recommendation_log")
public class RecommendationLog {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_procedure", referencedColumnName = "id", nullable = false)
    private Procedure procedure;

    private  String interactionType;
    private LocalDateTime interactionDate;


}
