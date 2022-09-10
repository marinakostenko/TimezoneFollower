package com.codemari.timezonefollowerrest.controllers;

import com.codemari.timezonefollowerrest.datamodels.AppUser;
import com.codemari.timezonefollowerrest.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository repository;


    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<AppUser> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping("/users")
    AppUser newUser(@RequestBody AppUser newAppUser) {
        return repository.save(newAppUser);
    }

    @GetMapping("/users/{id}")
    AppUser getUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    AppUser replaceUser(@RequestBody AppUser newAppUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(appUser -> {
                    appUser.setName(newAppUser.getName());
                    appUser.setLocation(newAppUser.getLocation());
                    appUser.setPhoneNumber(newAppUser.getPhoneNumber());
                    appUser.setTimeZone(newAppUser.getTimeZone());
                    return repository.save(appUser);
                })
                .orElseGet(() -> {
                    newAppUser.setId(id);
                    return repository.save(newAppUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteMapping(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
