package com.example.APP.Company.repository.users.user;

import com.example.APP.Company.domain.entity.users.user_professional.UserProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserProfessionalRepository extends JpaRepository<UserProfessional, UUID> {
    List<UserProfessional> findByUser_NameContainingIgnoreCase(String name);
    //List<UserProfessional> findByProfessions_NameContainingIgnoreCase(String professionName);
}
