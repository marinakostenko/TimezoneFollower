package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.request.CreateUserRequest;
import com.codemari.timezonefollowerrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class AuthController {
    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public AppUserDto register(@RequestBody @Valid CreateUserRequest createUserRequest) {
        AppUserDto userDto = new AppUserDto()
                .setEmail(createUserRequest.email())
                .setName(createUserRequest.name() == null ? "user name" : createUserRequest.name())
                .setPhoneNumber(createUserRequest.phoneNumber())
                .setCity(createUserRequest.city())
                .setCountry(createUserRequest.country())
                .setTimeZone(createUserRequest.timeZone());
        return this.userService.addUser(userDto);
    }
}
