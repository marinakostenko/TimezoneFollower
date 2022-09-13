package com.codemari.timezonefollowerrest.service;

import com.codemari.timezonefollowerrest.dao.LocationRepository;
import com.codemari.timezonefollowerrest.dao.UserRepository;
import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.LocationDto;
import com.codemari.timezonefollowerrest.dto.ModelToDto;
import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.model.Location;
import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private static final Logger log = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    public List<LocationDto> getAllLocations() {
        List<LocationDto> locations = new ArrayList<>(locationRepository.findAll().stream().map(ModelToDto::toLocationDto).toList());
        locations.sort(Comparator.comparing(LocationDto::getCity));

        return locations;
    }

}
