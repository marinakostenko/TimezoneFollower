package com.codemari.timezonefollowerrest.service;

import com.codemari.timezonefollowerrest.dao.FavouriteLocationRepository;
import com.codemari.timezonefollowerrest.dao.LocationRepository;
import com.codemari.timezonefollowerrest.dao.UserRepository;
import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.LocationDto;
import com.codemari.timezonefollowerrest.dto.ModelToDto;
import com.codemari.timezonefollowerrest.exception.DuplicatedFavouriteLocationException;
import com.codemari.timezonefollowerrest.exception.LocationNotFoundException;
import com.codemari.timezonefollowerrest.exception.UserNotFoundException;
import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.model.Contact;
import com.codemari.timezonefollowerrest.model.FavouriteLocation;
import com.codemari.timezonefollowerrest.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private static final Logger log = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavouriteLocationRepository favouriteLocationRepository;

    public LocationService() {
    }

    public List<LocationDto> getAllLocations(String city) {
        Optional<List<Location>> locationsDtoOptional = locationRepository.findByCityStartingWith(city);
        if (locationsDtoOptional.isEmpty()) {
            throw new LocationNotFoundException(city);
        }

        List<LocationDto> locations = new ArrayList<>(locationsDtoOptional.get().stream().map(ModelToDto::toLocationDto).toList());

        List<LocationDto> ret = locations.stream().filter(locationDto -> locationDto.getCity().equals(city)).collect(Collectors.toList());
        locations.sort(Comparator.comparing(LocationDto::getPopulation).reversed());

        int count = 0;
        while(count < 15 && count < locations.size()) {
            if(!ret.contains(locations.get(count))) {
                ret.add(locations.get(count));
            }
            count++;
        }

        return ret;
    }

    public LocationDto findLocationByName(String city, String country) {
        Optional<Location> location = locationRepository.findByCityAndCountry(city, country);

        if(location.isPresent()) {
            return ModelToDto.toLocationDto(location.get());
        }

        throw new LocationNotFoundException(city + " " + country);
    }

    public LocationDto findLocationByCoordinates(Double latitude, Double longitude) {
        log.info("lat {} lon {}", latitude, longitude);
        Optional<Location> location = locationRepository.findByCoordinates(latitude, longitude);

        if(location.isPresent()) {
            return ModelToDto.toLocationDto(location.get());
        }

        throw new LocationNotFoundException(latitude + " " + longitude);
    }

    public LocationDto findLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);

        if(location.isPresent()) {
            return ModelToDto.toLocationDto(location.get());
        }

        throw new LocationNotFoundException(String.valueOf(id));
    }

    public List<AppUserDto> findAllContactsByLocation(Long locationId, Long userId) {
        Optional<Location> location = locationRepository.findById(locationId);
        Optional<AppUser> appUser = userRepository.findById(userId);

        if(appUser.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(userId));
        }

        if(location.isPresent()) {
            List<AppUser> usersByLocation = location.get().getUsers();
            List<Contact> contacts = appUser.get().getContacts();
            List<AppUserDto> appUserDtosByLocation = new ArrayList<>();

            for(Contact contact : contacts) {
                Optional<AppUser> user = userRepository.findById(contact.getContactUser());

                if(user.isPresent() && usersByLocation.contains(user.get())) {
                    appUserDtosByLocation.add(ModelToDto.toAppUserDto(user.get()));
                }
            }

            return appUserDtosByLocation;
        }
        throw new LocationNotFoundException(String.valueOf(locationId));
    }

    public List<LocationDto> findAllFavouriteLocations(Long userId) {
        Optional<AppUser> appUser = userRepository.findById(userId);

        if(appUser.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(userId));
        }

        List<LocationDto> ret = new ArrayList<>();
        for (FavouriteLocation location : appUser.get().getFavouriteLocations()) {
            Optional<Location> locationModel = locationRepository.findById(location.getLocation());
            locationModel.ifPresent(value -> ret.add(ModelToDto.toLocationDto(value)));
        }
        return ret;
    }

    @Transactional
    public LocationDto addFavouriteLocation(Long userId, Long locationId) {
        Optional<AppUser> appUser = userRepository.findById(userId);

        if(appUser.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(userId));
        }

        Optional<Location> location = locationRepository.findById(locationId);

        if(location.isPresent()) {
            Optional<FavouriteLocation> existedLocation =
                    appUser.get().getFavouriteLocations().stream().filter(fLocation -> fLocation.getLocation().equals(location.get().getId())).findFirst();
            if(existedLocation.isEmpty()) {
                FavouriteLocation favouriteLocation = new FavouriteLocation().setLocation(location.get().getId()).setAppUser(appUser.get());
                favouriteLocationRepository.save(favouriteLocation);

                return ModelToDto.toLocationDto(location.get());
            } else {
                throw new DuplicatedFavouriteLocationException(String.valueOf(existedLocation.get().getLocation()));
            }
        }

        throw new LocationNotFoundException(String.valueOf(locationId));
    }

    @Transactional
    public LocationDto deleteFavouriteLocation(Long userId, Long locationId) {
        Optional<AppUser> appUser = userRepository.findById(userId);

        if(appUser.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(userId));
        }
        Optional<Location> location = locationRepository.findById(locationId);

        if(location.isPresent()) {
            Optional<FavouriteLocation> existedLocation =
                    appUser.get().getFavouriteLocations().stream().filter(fLocation -> fLocation.getLocation().equals(location.get().getId())).findFirst();

            existedLocation.ifPresent(favouriteLocation -> favouriteLocationRepository.delete(favouriteLocation));

            return ModelToDto.toLocationDto(location.get());
        }
        throw new LocationNotFoundException(String.valueOf(locationId));
    }

}
