package com.codemari.timezonefollowerrest.dao;

import com.codemari.timezonefollowerrest.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
