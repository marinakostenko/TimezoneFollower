package com.codemari.timezonefollowerrest.dto;

import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.model.Contact;
import com.codemari.timezonefollowerrest.model.Location;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ModelToDto {
    public static AppUserDto toAppUserDto(AppUser appUser) {

        return new AppUserDto()
                .setId(appUser.getId())
                .setName(appUser.getName())
                .setEmail(appUser.getEmail())
                .setPhoneNumber(appUser.getPhoneNumber())
                .setIsActive(appUser.getIsActive())
                .setCity(appUser.getLocation().getCity())
                .setTimeZone(appUser.getLocation().getTimeZone())
                .setContacts(new ArrayList<>(
                        appUser.getContacts().stream()
                                .map(contact -> new ModelMapper().map(contact, ContactDto.class))
                                .collect(Collectors.toList())));
    }

    public static LocationDto toLocationDto(Location location) {

        return new LocationDto()
                .setCity(location.getCity())
                .setCountry(location.getCountry())
                .setRegion(location.getRegion())
                .setTimeZone(location.getTimeZone())
                .setUsers(new HashSet<>(
                        location.getUsers().stream()
                                .map(user -> new ModelMapper().map(user, AppUserDto.class))
                                .collect(Collectors.toSet())
                ));
    }

    public static ContactDto toContactDto(Contact contact) {
        return new ContactDto().setContactId(contact.getContactId());
    }


}
