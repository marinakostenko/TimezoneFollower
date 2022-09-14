package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.rest.request.UpdateUserContactsRequest;
import com.codemari.timezonefollowerrest.rest.request.UserAuthenticateRequest;
import com.codemari.timezonefollowerrest.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new user")
    public Long authenticate(@RequestBody @Valid UserAuthenticateRequest userAuthenticateRequest) {
        AppUserDto userDto = new AppUserDto()
                .setEmail(userAuthenticateRequest.getEmail())
                .setName(userAuthenticateRequest.getName() == null ? "user name" : userAuthenticateRequest.getName())
                .setPhoneNumber(userAuthenticateRequest.getPhoneNumber())
                .setCity(userAuthenticateRequest.getCity())
                .setCountry(userAuthenticateRequest.getCountry())
                .setRegion(userAuthenticateRequest.getRegion())
                .setTimeZone(userAuthenticateRequest.getTimeZone());

        userDto = this.userService.addUser(userDto);

        return userDto.getId();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single user", notes = "User ID is required", authorizations = {@Authorization(value = "apiKey")})
    public AppUserDto getUserById(@ApiParam(value = "User ID", required = true)
                                  @PathVariable Long id) {
        return this.userService.findUserById(id);
    }

    @GetMapping("/{number}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single user", notes = "User phone number is required", authorizations = {@Authorization(value = "apiKey")})
    public AppUserDto getUserByPhoneNumber(@ApiParam(value = "User phone number", required = true)
                                           @PathVariable String phoneNumber) {
        return this.userService.findUserByPhoneNumber(phoneNumber);
    }

    @GetMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a user contacts", notes = "User model is required", authorizations = {@Authorization(value = "apiKey")})
    public List<AppUserDto> getUserContacts(AppUserDto appUserDto) {
        return this.userService.getUserContacts(appUserDto);
    }

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a user contacts", notes = "User phone number and email are required", authorizations = {@Authorization(value = "apiKey")})
    public List<AppUserDto> updateUserContacts(@RequestBody @Valid UpdateUserContactsRequest userContactsRequest) {
        AppUserDto userDto = new AppUserDto()
                .setEmail(userContactsRequest.getEmail())
                .setPhoneNumber(userContactsRequest.getPhoneNumber());

        return this.userService.updateUserContacts(userDto, userContactsRequest.getContacts());
    }


    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update user", authorizations = {@Authorization(value = "apiKey")})
    public AppUserDto updateUser(@ApiParam(value = "The phone number and email of existing user", required = true)
                                 @RequestBody @Valid UserAuthenticateRequest userAuthenticateRequest) {
        AppUserDto userDto = new AppUserDto()
                .setEmail(userAuthenticateRequest.getEmail())
                .setName(userAuthenticateRequest.getName() == null ? "user name" : userAuthenticateRequest.getName())
                .setPhoneNumber(userAuthenticateRequest.getPhoneNumber())
                .setCity(userAuthenticateRequest.getCity())
                .setCountry(userAuthenticateRequest.getCountry())
                .setRegion(userAuthenticateRequest.getRegion())
                .setTimeZone(userAuthenticateRequest.getTimeZone());

        userDto = this.userService.updateUser(userDto);

        return userDto;
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete user", authorizations = {@Authorization(value = "apiKey")})
    public void deleteUser(@ApiParam(value = "The existing user entity", required = true)
                           @RequestBody @Valid AppUserDto appUserDto) {
        this.userService.deleteUser(appUserDto);
    }

}
