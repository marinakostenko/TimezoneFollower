package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.LocationDto;
import com.codemari.timezonefollowerrest.rest.request.FindContactsByLocationRequest;
import com.codemari.timezonefollowerrest.rest.request.FindLocationRequest;
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
  //  @ApiOperation(value = "Get all locations", authorizations = {@Authorization(value = "apiKey")})
    public List<LocationDto> getAllLocations() {
        return this.locationService.getAllLocations();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
  //  @ApiOperation(value = "Find location by name", notes = "City, region and Country are required", authorizations = {@Authorization(value = "apiKey")})
    public LocationDto getLocationByName(@RequestBody @Valid FindLocationRequest locationRequest) {
        return this.locationService.findLocationByName(locationRequest.getCity(), locationRequest.getCountry(), locationRequest.getRegion());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
 //   @ApiOperation(value = "Find location by id", notes = "Location id is required", authorizations = {@Authorization(value = "apiKey")})
    public LocationDto getLocationById(@PathVariable Long location_id) {
        return this.locationService.findLocationById(location_id);
    }

    @GetMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
 //   @ApiOperation(value = "Find location contacts", authorizations = {@Authorization(value = "apiKey")})
    public List<AppUserDto> getContactsByLocation(@RequestBody @Valid FindContactsByLocationRequest contactsByLocationRequest) {
        return this.locationService.findAllContactsByLocation(contactsByLocationRequest.getLocation_id(),
                new AppUserDto().setPhoneNumber(contactsByLocationRequest.getPhoneNumber()).setEmail(contactsByLocationRequest.getEmail()));
    }

    @GetMapping("/favourite")
    @ResponseStatus(HttpStatus.OK)
 //   @ApiOperation(value = "Find favourite user locations", authorizations = {@Authorization(value = "apiKey")})
    public List<LocationDto> getFavouriteLocations(AppUserDto appUserDto) {
        return this.locationService.findAllFavouriteLocations(appUserDto);
    }

    @PostMapping("/favourite/{id}")
    @ResponseStatus(HttpStatus.CREATED)
  //  @ApiOperation(value = "Add new favourite location", notes = "User model and location id are required", authorizations = {@Authorization(value = "apiKey")})
    public LocationDto addFavouriteLocation(AppUserDto appUserDto,
                                            @PathVariable Long id) {

        return this.locationService.addFavouriteLocation(appUserDto, id);
    }

    @DeleteMapping("/favourite/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
  //  @ApiOperation(value = "Remove favourite location", notes = "User model and location id are required", authorizations = {@Authorization(value = "apiKey")})
    public LocationDto deleteFavouriteLocation(AppUserDto appUserDto,
                                               @PathVariable Long id) {

        return this.locationService.deleteFavouriteLocation(appUserDto, id);
    }
}
