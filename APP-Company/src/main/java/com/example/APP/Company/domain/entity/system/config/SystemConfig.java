package com.example.APP.Company.domain.entity.system.config;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_system_config")
public class SystemConfig {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @Column(name = "config_key")
    private String key;
    private String value;
    private LocalDateTime updatedAt;

}
