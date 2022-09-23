package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.request.CreateUserRequest;
import com.codemari.timezonefollowerrest.dto.request.UpdateUserContactsRequest;
import com.codemari.timezonefollowerrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserDto getUserById(@PathVariable String userId) {
        return this.userService.findUserById(Long.parseLong(userId));
    }

    @GetMapping("/phone/{userNumber}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserDto getUserByPhoneNumber(@PathVariable String userNumber) {
        return this.userService.findUserByPhoneNumber(userNumber);
    }

    @GetMapping("/contacts/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserDto> getUserContacts(@PathVariable String userId) {
        return this.userService.getUserContacts(Long.parseLong(userId));
    }

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserDto> updateUserContacts(@RequestBody @Valid UpdateUserContactsRequest userContactsRequest) {
        return this.userService.updateUserContacts(Long.parseLong(userContactsRequest.userId()), userContactsRequest.contacts());
    }


    @PutMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AppUserDto updateUser(@RequestBody @Valid CreateUserRequest userAuthenticateRequest) {
        AppUserDto userDto = new AppUserDto()
                .setEmail(userAuthenticateRequest.email())
                .setName(userAuthenticateRequest.name() == null ? "user name" : userAuthenticateRequest.name())
                .setPhoneNumber(userAuthenticateRequest.phoneNumber())
                .setCity(userAuthenticateRequest.city())
                .setCountry(userAuthenticateRequest.country())
                .setTimeZone(userAuthenticateRequest.timeZone());

        userDto = this.userService.updateUser(userDto);

        return userDto;
    }

    @PutMapping("/location/{userId}/{latitude}/{longitude}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AppUserDto updateUserLocation(@PathVariable String userId,
                                            @PathVariable String latitude,
                                            @PathVariable String longitude) {

        return this.userService.updateUserLocation(Long.parseLong(userId), Double.parseDouble(latitude),
                Double.parseDouble(longitude));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(String userId) {
        this.userService.deleteUser(Long.parseLong(userId));
    }

}
