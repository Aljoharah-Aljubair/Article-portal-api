package com.artical.portal.api.repository;

import com.artical.portal.api.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    // Method to find a user by their email
    @Query("SELECT a FROM UserEntity a WHERE a.Email = ?1")
    UserEntity findByEmail(String email);

    // Method to find a user by their username

    @Query("SELECT a FROM UserEntity a WHERE a.Username = ?1")
    UserEntity findByUsername(String username);
}
