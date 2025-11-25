package com.example.APP.Company.domain.entity.security.audit_log;

import com.example.APP.Company.domain.entity.users.user.User;
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
@Table(name="tb_audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user_id;

    private String action;

    private String description;

    private LocalDateTime action_date;


}
