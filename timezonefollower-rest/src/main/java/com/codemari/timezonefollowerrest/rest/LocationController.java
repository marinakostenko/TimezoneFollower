package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.LocationDto;
import com.codemari.timezonefollowerrest.dto.request.FindContactsByLocationRequest;
import com.codemari.timezonefollowerrest.dto.request.FindLocationRequest;
import com.codemari.timezonefollowerrest.dto.request.UserFavouriteLocationsRequest;
import com.codemari.timezonefollowerrest.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto getLocationByName(@RequestBody @Valid FindLocationRequest locationRequest) {
        return this.locationService.findLocationByName(locationRequest.city(), locationRequest.country(), locationRequest.region());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto getLocationById(@PathVariable Long location_id) {
        return this.locationService.findLocationById(location_id);
    }

    @GetMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserDto> getContactsByLocation(@RequestBody @Valid FindContactsByLocationRequest contactsByLocationRequest) {
        return this.locationService.findAllContactsByLocation(contactsByLocationRequest.locationId(),
                new AppUserDto().setPhoneNumber(contactsByLocationRequest.phoneNumber()).setEmail(contactsByLocationRequest.email()));
    }

    @GetMapping("/favourite")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDto> getFavouriteLocations(@RequestBody @Valid UserFavouriteLocationsRequest favouriteLocationsRequest) {
        return this.locationService
                .findAllFavouriteLocations(new AppUserDto().setEmail(favouriteLocationsRequest.email()).setPhoneNumber(favouriteLocationsRequest.phoneNumber()));
    }

    @PostMapping("/favourite/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto addFavouriteLocation(@RequestBody @Valid UserFavouriteLocationsRequest favouriteLocationsRequest,
                                            @PathVariable Long id) {

        return this.locationService.addFavouriteLocation(new AppUserDto().setEmail(favouriteLocationsRequest.email()).setPhoneNumber(favouriteLocationsRequest.phoneNumber()), id);
    }

    @DeleteMapping("/favourite/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public LocationDto deleteFavouriteLocation(@RequestBody @Valid UserFavouriteLocationsRequest favouriteLocationsRequest,
                                               @PathVariable Long id) {

        return this.locationService.deleteFavouriteLocation(new AppUserDto().setEmail(favouriteLocationsRequest.email()).setPhoneNumber(favouriteLocationsRequest.phoneNumber()), id);
    }
}
