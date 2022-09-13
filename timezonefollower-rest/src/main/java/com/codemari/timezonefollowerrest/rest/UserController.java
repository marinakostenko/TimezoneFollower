package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a list of all users")
    public List<AppUser> getAllUsers() {
        return this.userService.getAllUsers();
    }

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation(value = "Create a new user")
//    public AppUser newUser(@RequestBody AppUser newAppUser) {
//        return this.userService.addUser(newAppUser);
//    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Get a single user", notes = "User ID is required")
//    public AppUser getUser(@ApiParam(value = "User ID", required = true)
//                    @PathVariable Long id) {
//        return this.userService.getUser(id);
//    }

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ApiOperation(value = "Update user")
//    public AppUser updateUser(@ApiParam(value = "The id of existing user", required = true)
//                       @PathVariable Long id, @RequestBody AppUser newAppUser) {
//        return this.userService.updateUser(id, newAppUser);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete user")
    public void deleteUser(
            @PathVariable Long id) {
        this.userService.deleteUser(id);
    }

}
