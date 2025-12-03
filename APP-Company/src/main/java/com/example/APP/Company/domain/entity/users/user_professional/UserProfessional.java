package com.example.APP.Company.domain.entity.users.user_professional;

import com.example.APP.Company.domain.entity.users.establishment.Establishment;
import com.example.APP.Company.domain.entity.users.professions.Professions;
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
@Table(name="tb_user_professional")
public class UserProfessional {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_professions", referencedColumnName = "id", nullable = false)
    private Professions professionId;

    @ManyToOne
    @JoinColumn(name = "id_establishment", referencedColumnName = "id", nullable = false)
    private Establishment establishmentId;

    private String bio;

    private String imageUrl;

    private LocalDateTime created_at;






}
