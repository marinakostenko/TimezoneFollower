package com.codemari.timezonefollowerrest.dto;

import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.model.Location;

public class ModelToDto {
    public static AppUserDto toAppUserDto(AppUser appUser) {

        AppUserDto appUserDto =  new AppUserDto()
                .setId(appUser.getId())
                .setName(appUser.getName())
                .setEmail(appUser.getEmail())
                .setPhoneNumber(appUser.getPhoneNumber())
                .setIsActive(appUser.getIsActive());

        if(appUser.getLocation() != null) {
            appUserDto
                    .setCity(appUser.getLocation().getCity())
                    .setCountry(appUser.getLocation().getCountry())
                    .setTimeZone(appUser.getLocation().getTimezone());
        }

        return appUserDto;
    }

    public static LocationDto toLocationDto(Location location) {
        return new LocationDto()
                .setId(location.getId())
                .setCity(location.getCity())
                .setCountry(location.getCountry())
                .setCountryCode(location.getCountryCode())
                .setTimezone(location.getTimezone())
                .setLat(location.getLatitude())
                .setLon(location.getLongitude());
    }


}
