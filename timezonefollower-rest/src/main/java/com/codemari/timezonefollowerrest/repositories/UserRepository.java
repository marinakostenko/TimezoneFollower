package com.codemari.timezonefollowerrest.repositories;

import com.codemari.timezonefollowerrest.datamodels.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
