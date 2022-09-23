package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.LocationDto;
import com.codemari.timezonefollowerrest.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDto> getAllLocations() {
        return this.locationService.getAllLocations();
    }

    @GetMapping("name/{city}/{country}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto getLocationByName(@PathVariable String city, @PathVariable String country) {
        return this.locationService.findLocationByName(city, country);
    }

    @GetMapping("coordinates/{latitude}/{longitude}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto getLocationByCoordinates(@PathVariable String latitude, @PathVariable String longitude) {
        return this.locationService.findLocationByCoordinates(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    @GetMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto getLocationById(@PathVariable String locationId) {
        return this.locationService.findLocationById(Long.parseLong(locationId));
    }

    @GetMapping("/contacts/{userId}/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserDto> getContactsByLocation(@PathVariable String userId, @PathVariable String locationId) {
        return this.locationService.findAllContactsByLocation(Long.parseLong(locationId), Long.parseLong(userId));
    }

    @GetMapping("/favourite/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDto> getFavouriteLocations(@PathVariable String userId) {
        return this.locationService.findAllFavouriteLocations(Long.parseLong(userId));
    }

    @PostMapping("/favourite/{userId}/{locationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto addFavouriteLocation(@PathVariable String userId,
                                            @PathVariable String locationId) {

        return this.locationService.addFavouriteLocation(Long.parseLong(userId), Long.parseLong(locationId));
    }

    @DeleteMapping("/favourite/{userId}/{locationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public LocationDto deleteFavouriteLocation(@PathVariable String userId,
                                               @PathVariable String locationId) {

        return this.locationService.deleteFavouriteLocation(Long.parseLong(userId), Long.parseLong(locationId));
    }
}
