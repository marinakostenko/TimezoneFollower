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

//    @PostMapping("/authenticate")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation(value = "Create a new user")
//    public Long authenticate(@RequestBody @Valid UserAuthenticateRequest userAuthenticateRequest) {
//        AppUserDto userDto = new AppUserDto()
//                .setEmail(userAuthenticateRequest.getEmail())
//                .setName(userAuthenticateRequest.getName() == null ? "user name" : userAuthenticateRequest.getName())
//                .setPhoneNumber(userAuthenticateRequest.getPhoneNumber())
//                .setCity(userAuthenticateRequest.getCity())
//                .setCountry(userAuthenticateRequest.getCountry())
//                .setRegion(userAuthenticateRequest.getRegion())
//                .setTimeZone(userAuthenticateRequest.getTimeZone());
//
//        userDto = this.userService.addUser(userDto);
//
//        return userDto.getId();
//    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserDto getUserById(@PathVariable String id) {
        return this.userService.findUserById(Long.parseLong(id));
    }

    @GetMapping("/phone/{userNumber}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserDto getUserByPhoneNumber(@PathVariable String userNumber) {
        return this.userService.findUserByPhoneNumber(userNumber);
    }

    @GetMapping("/contacts/{userNumber}/{userEmail}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserDto> getUserContacts(@PathVariable String userNumber, @PathVariable String userEmail) {
        return this.userService.getUserContacts(new AppUserDto().setPhoneNumber(userNumber).setEmail(userEmail));
    }

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserDto> updateUserContacts(@RequestBody @Valid UpdateUserContactsRequest userContactsRequest) {
        AppUserDto userDto = new AppUserDto()
                .setEmail(userContactsRequest.email())
                .setPhoneNumber(userContactsRequest.phoneNumber());

        return this.userService.updateUserContacts(userDto, userContactsRequest.contacts());
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
                .setRegion(userAuthenticateRequest.region())
                .setTimeZone(userAuthenticateRequest.timeZone());

        userDto = this.userService.updateUser(userDto);

        return userDto;
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestBody @Valid AppUserDto appUserDto) {
        this.userService.deleteUser(appUserDto);
    }

}
