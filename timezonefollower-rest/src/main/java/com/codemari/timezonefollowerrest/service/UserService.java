package com.codemari.timezonefollowerrest.service;

import com.codemari.timezonefollowerrest.dao.UserRepository;
import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public AppUser addUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public AppUser updateUser(Long id, AppUser newAppUser) {
        return userRepository.findById(id)
                .map(appUser -> {
                    appUser.setName(newAppUser.getName());
                    appUser.setEmail(newAppUser.getEmail());
                    appUser.setPhoneNumber(newAppUser.getPhoneNumber());
                    return userRepository.save(appUser);
                })
                .orElseGet(() -> {
                    newAppUser.setId(id);
                    return userRepository.save(newAppUser);
                });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
