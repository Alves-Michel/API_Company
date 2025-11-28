package com.example.APP.Company.repository.users.user;

import com.example.APP.Company.domain.entity.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);
    List<User> findByNameContainingIgnoreCase(String name);

    @Query("""
    SELECT u FROM User u
    WHERE u.userName = :login
       OR u.email = :login
       OR u.phoneNumber = :login
""")
    Optional<User> findByLogin(String login);
}
