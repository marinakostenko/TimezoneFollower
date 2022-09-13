package com.codemari.timezonefollowerrest.dao;

import com.codemari.timezonefollowerrest.model.FavouriteLocation;
import com.codemari.timezonefollowerrest.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteLocationRepository extends JpaRepository<FavouriteLocation, Long> {

}
