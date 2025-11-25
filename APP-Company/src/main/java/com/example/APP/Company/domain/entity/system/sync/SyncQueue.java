package com.example.APP.Company.domain.entity.system.sync;

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
@Table(name="tb_sync_queue")
public class SyncQueue {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    private String entityName;

    private UUID entityId;

    private String operation;
    private boolean processed;
    private LocalDateTime processedAt;


}
