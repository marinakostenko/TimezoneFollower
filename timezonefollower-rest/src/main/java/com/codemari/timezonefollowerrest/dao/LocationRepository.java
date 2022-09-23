package com.codemari.timezonefollowerrest.dao;

import com.codemari.timezonefollowerrest.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByCityAndCountry(String city, String country);

    @Query( nativeQuery = true,
            value = "SELECT * " +
                    "( 3959 * acos( cos( radians(:lat) ) * cos( radians( latitude ) ) * " +
                    "cos( radians( longitude ) - radians(:lon) ) + sin( radians(:lat) ) * " +
                    "sin( radians( latitude ) ) ) ) AS distance " +
                    "FROM location " +
                    "ORDER BY distance" +
                    "LIMIT 1;"
    )
    Optional<Location> findByCoordinates(@Param("lat") Double latitude, @Param("lon") Double longitude);
}
