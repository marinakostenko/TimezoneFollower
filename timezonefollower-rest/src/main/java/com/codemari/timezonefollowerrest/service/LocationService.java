package com.codemari.timezonefollowerrest.service;

import com.codemari.timezonefollowerrest.dao.ContactRepository;
import com.codemari.timezonefollowerrest.dao.FavouriteLocationRepository;
import com.codemari.timezonefollowerrest.dao.LocationRepository;
import com.codemari.timezonefollowerrest.dao.UserRepository;
import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.LocationDto;
import com.codemari.timezonefollowerrest.dto.ModelToDto;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private static final Logger log = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    FavouriteLocationRepository favouriteLocationRepository;

    public List<LocationDto> getAllLocations() {
        List<LocationDto> locations = new ArrayList<>(locationRepository.findAll().stream().map(ModelToDto::toLocationDto).toList());
        locations.sort(Comparator.comparing(LocationDto::getCity));

        return locations;
    }

    public LocationDto findLocationByName(String city, String region, String country) {
        Location location = locationRepository.findByCityAndRegionAndCountry(city, region, country);

        if(location != null) {
            return ModelToDto.toLocationDto(location);
        }

        throw new LocationNotFoundException(city + " " + region + " " + country);
    }

    public LocationDto findLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);

        if(location.isPresent()) {
            return ModelToDto.toLocationDto(location.get());
        }

        throw new LocationNotFoundException(String.valueOf(id));
    }

    public List<AppUserDto> findAllContactsByLocation(Long locationId, AppUserDto appUserDto) {
        Optional<Location> location = locationRepository.findById(locationId);
        AppUser appUser = userRepository.findByPhoneNumber(appUserDto.getPhoneNumber());

        if(appUser == null) {
            throw new UserNotFoundException(appUserDto.getPhoneNumber());
        }

        if(location.isPresent()) {
            List<AppUser> usersByLocation = location.get().getUsers();
            List<Contact> contacts = appUser.getContacts();
            List<AppUserDto> appUserDtosByLocation = new ArrayList<>();

            for(Contact contact : contacts) {
                AppUser user = contact.getContactUser();

                if(user != null && usersByLocation.contains(user)) {
                    appUserDtosByLocation.add(ModelToDto.toAppUserDto(user));
                }
            }

            return appUserDtosByLocation;
        }
        throw new LocationNotFoundException(String.valueOf(locationId));
    }

    public List<LocationDto> findAllFavouriteLocations(AppUserDto appUserDto) {
        AppUser appUser = userRepository.findByPhoneNumber(appUserDto.getPhoneNumber());

        if(appUser == null) {
            throw new UserNotFoundException(appUserDto.getPhoneNumber());
        }
        return appUser
                .getFavouriteLocations()
                .stream()
                .map(location -> ModelToDto.toLocationDto(location.getLocation())).toList();
    }

    @Transactional
    public LocationDto addFavouriteLocation(AppUserDto appUserDto, LocationDto locationDto) {
        AppUser appUser = userRepository.findByPhoneNumber(appUserDto.getPhoneNumber());

        if(appUser == null) {
            throw new UserNotFoundException(appUserDto.getPhoneNumber());
        }

        Location location = locationRepository.findByCityAndRegionAndCountry(locationDto.getCity(), locationDto.getRegion(), locationDto.getCountry());

        if(location != null) {
            Optional<FavouriteLocation> existedLocation =
                    appUser.getFavouriteLocations().stream().filter(fLocation -> fLocation.getLocation().equals(location)).findFirst();
            if(existedLocation.isEmpty()) {
                FavouriteLocation favouriteLocation = new FavouriteLocation().setLocation(location).setAppUser(appUser);
                favouriteLocationRepository.save(favouriteLocation);

                return locationDto;
            }
        }

        throw new LocationNotFoundException(locationDto.getCity() + " " + locationDto.getRegion() + " " + locationDto.getCountry());
    }

    @Transactional
    public LocationDto deleteFavouriteLocation(AppUserDto appUserDto, LocationDto locationDto) {
        AppUser appUser = userRepository.findByPhoneNumber(appUserDto.getPhoneNumber());

        if(appUser == null) {
            throw new UserNotFoundException(appUserDto.getPhoneNumber());
        }
        Location location = locationRepository.findByCityAndRegionAndCountry(locationDto.getCity(), locationDto.getRegion(), locationDto.getCountry());

        if(location != null) {
            Optional<FavouriteLocation> existedLocation =
                    appUser.getFavouriteLocations().stream().filter(fLocation -> fLocation.getLocation().equals(location)).findFirst();

            if(existedLocation.isPresent()) {
                locationRepository.delete(location);
            }

            return locationDto;
        }
        throw new LocationNotFoundException(locationDto.getCity() + " " + locationDto.getRegion() + " " + locationDto.getCountry());
    }

}
