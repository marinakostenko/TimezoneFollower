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
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
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

    @GetMapping("/{city}/{country}/{region}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto getLocationByName(@PathVariable String city, @PathVariable String region,     @PathVariable String country) {
        return this.locationService.findLocationByName(city, region, country);
    }

    @GetMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto getLocationById(@PathVariable String locationId) {
        return this.locationService.findLocationById(Long.parseLong(locationId));
    }

    @GetMapping("/contacts/{locationId}/{userNumber}/{userEmail}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserDto> getContactsByLocation(@PathVariable String locationId, @PathVariable String userNumber, @PathVariable String userEmail) {
        return this.locationService.findAllContactsByLocation(Long.parseLong(locationId),
                new AppUserDto().setPhoneNumber(userNumber).setEmail(userEmail));
    }

    @GetMapping("/favourite/{userNumber}/{userEmail}")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDto> getFavouriteLocations(@PathVariable String userNumber, @PathVariable String userEmail) {
        return this.locationService
                .findAllFavouriteLocations(new AppUserDto().setEmail(userEmail).setPhoneNumber(userNumber));
    }

    @PostMapping("/favourite/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto addFavouriteLocation(@RequestBody @Valid UserFavouriteLocationsRequest favouriteLocationsRequest,
                                            @PathVariable String id) {

        return this.locationService.addFavouriteLocation(new AppUserDto().setEmail(favouriteLocationsRequest.email()).setPhoneNumber(favouriteLocationsRequest.phoneNumber()), Long.parseLong(id));
    }

    @DeleteMapping("/favourite/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public LocationDto deleteFavouriteLocation(@RequestBody @Valid UserFavouriteLocationsRequest favouriteLocationsRequest,
                                               @PathVariable String id) {

        return this.locationService.deleteFavouriteLocation(new AppUserDto().setEmail(favouriteLocationsRequest.email()).setPhoneNumber(favouriteLocationsRequest.phoneNumber()), Long.parseLong(id));
    }
}
