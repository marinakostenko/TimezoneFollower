package com.codemari.timezonefollowerrest.service;

import com.codemari.timezonefollowerrest.dao.ContactRepository;
import com.codemari.timezonefollowerrest.dao.LocationRepository;
import com.codemari.timezonefollowerrest.dao.UserRepository;
import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.ModelToDto;
import com.codemari.timezonefollowerrest.exception.DuplicatedUserException;
import com.codemari.timezonefollowerrest.exception.UserNotFoundException;
import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.model.Contact;
import com.codemari.timezonefollowerrest.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private LocationRepository locationRepository;

    public UserService() {
    }

    @Transactional
    public AppUserDto addUser(AppUserDto userDto) {
        Optional<AppUser> user = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        AppUser newUser;
        if (user.isEmpty() || !user.get().getIsActive()) {
            Optional<Location> location = locationRepository.findByCityAndRegionAndCountry(userDto.getCity(), userDto.getRegion(), userDto.getCountry());

            if (user.isEmpty()) {
                newUser = new AppUser()
                        .setName(userDto.getName())
                        .setEmail(userDto.getEmail())
                        .setPhoneNumber(userDto.getPhoneNumber())
                        .setIsActive(true);
                location.ifPresent(newUser::setLocation);
            } else {
                newUser = user.get();
                newUser
                        .setName(userDto.getName())
                        .setEmail(userDto.getEmail())
                        .setIsActive(true);
                location.ifPresent(newUser::setLocation);
            }

            return ModelToDto.toAppUserDto(userRepository.save(newUser));
        }

        throw new DuplicatedUserException(userDto.getPhoneNumber());
    }

    public AppUserDto findUserByPhoneNumber(String phoneNumber) {
        Optional<AppUser> appUser = userRepository.findByPhoneNumber(phoneNumber);
        if (appUser.isPresent()) {
            log.info("User is present {}", appUser.get().getPhoneNumber());
            return ModelToDto.toAppUserDto(appUser.get());
        }

        throw new UserNotFoundException(phoneNumber);
    }

    public AppUserDto findUserById(Long id) {
        Optional<AppUser> appUser = userRepository.findById(id);
        if (appUser.isPresent()) {
            log.info("User is present {}", appUser.get().getPhoneNumber());
            return ModelToDto.toAppUserDto(appUser.get());
        }

        throw new UserNotFoundException(String.valueOf(id));
    }

    @Transactional
    public AppUserDto updateUser(AppUserDto userDtoUpdated) {
        Optional<AppUser> user = userRepository.findByEmail(userDtoUpdated.getEmail());

        if (user.isPresent()) {
            Optional<Location> location = locationRepository.findByCityAndRegionAndCountry(userDtoUpdated.getCity(), userDtoUpdated.getRegion(), userDtoUpdated.getCountry());

            AppUser userModel = user.get();
            userModel
                    .setName(userDtoUpdated.getName())
                    .setEmail(userDtoUpdated.getEmail())
                    .setPhoneNumber(userDtoUpdated.getPhoneNumber());

            location.ifPresent(userModel::setLocation);

            userRepository.save(userModel);

            return ModelToDto.toAppUserDto(userModel);
        }

        throw new UserNotFoundException(userDtoUpdated.getEmail());
    }

    @Transactional
    public List<AppUserDto> updateUserContacts(Long userId, List<String> contacts) {
        Optional<AppUser> user = userRepository.findById(userId);
        List<AppUserDto> contactList = new ArrayList<>();
        if (user.isPresent()) {
            for (String number : contacts) {
                Optional<AppUser> contactAppUser = userRepository.findByPhoneNumber(number);
                if (contactAppUser.isEmpty()) {
                    contactAppUser = Optional.ofNullable(new AppUser().setPhoneNumber(number).setIsActive(false));
                    contactAppUser.ifPresent(appUser -> userRepository.save(appUser));
                }

                if (contactAppUser.isPresent()) {
                    Optional<Contact> contact = contactRepository.findByContactUserAndMainUser(contactAppUser.get().getId(), user.get());

                    if (contact.isEmpty()) {
                        contact = Optional.ofNullable(new Contact().setContactUser(contactAppUser.get().getId()).setMainUser(user.get()));
                    }

                    contact.ifPresent(cont -> contactRepository.save(cont));
                    contactList.add(ModelToDto.toAppUserDto(contactAppUser.get()));
                }
            }

            return contactList;
        }

        throw new UserNotFoundException(String.valueOf(userId));
    }

    public List<AppUserDto> getUserContacts(Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);

        List<AppUserDto> contactList = new ArrayList<>();

        if (user.isPresent()) {
            List<Contact> contacts = user.get().getContacts();

            for (Contact contact : contacts) {
                Optional<AppUser> contactUser = userRepository.findById(contact.getContactUser());

                contactUser.ifPresent(appUser -> contactList.add(ModelToDto.toAppUserDto(contactUser.get())));
            }

            return contactList;
        }

        throw new UserNotFoundException(String.valueOf(userId));
    }

    @Transactional
    public AppUserDto deleteUser(Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        if (user.isPresent()) {
            if(user.get().getPhoneNumber() != null) {
                user.get().setIsActive(false);
                userRepository.save(user.get());
            } else {
                userRepository.deleteById(user.get().getId());
            }

            return ModelToDto.toAppUserDto(user.get());
        }
        throw new UserNotFoundException(userId.toString());
    }
}
