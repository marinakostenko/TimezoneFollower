package com.codemari.timezonefollowerrest.service;

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
                Optional<AppUser> user = userRepository.findById(contact.getContactUser());

                if(user.isPresent() && usersByLocation.contains(user.get())) {
                    appUserDtosByLocation.add(ModelToDto.toAppUserDto(user.get()));
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

        List<LocationDto> ret = new ArrayList<>();
        for (FavouriteLocation location : appUser.getFavouriteLocations()) {
            Optional<Location> locationModel = locationRepository.findById(location.getLocation());
            locationModel.ifPresent(value -> ret.add(ModelToDto.toLocationDto(value)));
        }
        return ret;
    }

    @Transactional
    public LocationDto addFavouriteLocation(AppUserDto appUserDto, Long locationId) {
        AppUser appUser = userRepository.findByPhoneNumber(appUserDto.getPhoneNumber());

        if(appUser == null) {
            throw new UserNotFoundException(appUserDto.getPhoneNumber());
        }

        Optional<Location> location = locationRepository.findById(locationId);

        if(location.isPresent()) {
            Optional<FavouriteLocation> existedLocation =
                    appUser.getFavouriteLocations().stream().filter(fLocation -> fLocation.getLocation().equals(location.get().getId())).findFirst();
            if(existedLocation.isEmpty()) {
                FavouriteLocation favouriteLocation = new FavouriteLocation().setLocation(location.get().getId()).setAppUser(appUser);
                favouriteLocationRepository.save(favouriteLocation);

                return ModelToDto.toLocationDto(location.get());
            }
        }

        throw new LocationNotFoundException(String.valueOf(locationId));
    }

    @Transactional
    public LocationDto deleteFavouriteLocation(AppUserDto appUserDto, Long locationId) {
        AppUser appUser = userRepository.findByPhoneNumber(appUserDto.getPhoneNumber());

        if(appUser == null) {
            throw new UserNotFoundException(appUserDto.getPhoneNumber());
        }
        Optional<Location> location = locationRepository.findById(locationId);

        if(location.isPresent()) {
            Optional<FavouriteLocation> existedLocation =
                    appUser.getFavouriteLocations().stream().filter(fLocation -> fLocation.getLocation().equals(location.get().getId())).findFirst();

            if(existedLocation.isPresent()) {
                locationRepository.delete(location.get());
            }

            return ModelToDto.toLocationDto(location.get());
        }
        throw new LocationNotFoundException(String.valueOf(locationId));
    }

}
