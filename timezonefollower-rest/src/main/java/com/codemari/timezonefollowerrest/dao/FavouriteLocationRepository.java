package com.codemari.timezonefollowerrest.dao;

import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.model.Contact;
import com.codemari.timezonefollowerrest.model.FavouriteLocation;
import com.codemari.timezonefollowerrest.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteLocationRepository extends JpaRepository<FavouriteLocation, Long> {
    Optional<FavouriteLocation> findByLocationAndAppUser(Long location, AppUser appUser);
}
