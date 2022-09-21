package com.codemari.timezonefollowerrest.dao;

import com.codemari.timezonefollowerrest.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByPhoneNumber(String phoneNumber);
    Optional<AppUser> findByEmail(String email);
}
