package com.example.APP.Company.domain.entity.user_professional;

import com.example.APP.Company.domain.entity.establishment.Establishment;
import com.example.APP.Company.domain.entity.professions.Professions;
import com.example.APP.Company.domain.entity.users.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_professions", referencedColumnName = "id", nullable = false)
    private Professions profession;

    @ManyToOne
    @JoinColumn(name = "id_establishment", referencedColumnName = "id", nullable = false)
    private Establishment establishment;

    private String bio;

    private String imageUrl;

    private LocalDateTime created_at;






}
