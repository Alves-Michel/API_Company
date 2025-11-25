package com.example.APP.Company.domain.entity.system.config;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_system_config")
public class SystemConfig {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    private String key;
    private String value;
    private LocalDateTime updatedAt;

}
