package com.example.APP.Company.repository.users.user;

import com.example.APP.Company.domain.entity.users.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByMail(String mail);
}
