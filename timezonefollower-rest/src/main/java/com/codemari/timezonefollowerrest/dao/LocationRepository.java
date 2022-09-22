package com.codemari.timezonefollowerrest.dao;

import com.codemari.timezonefollowerrest.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByCityAndRegionAndCountry(String city, String region, String country);
}
