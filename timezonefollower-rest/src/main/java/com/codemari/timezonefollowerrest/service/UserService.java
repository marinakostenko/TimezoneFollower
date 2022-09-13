package com.codemari.timezonefollowerrest.service;

import com.codemari.timezonefollowerrest.dao.ContactRepository;
import com.codemari.timezonefollowerrest.dao.UserRepository;
import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.ModelToDto;
import com.codemari.timezonefollowerrest.exception.DuplicatedUserException;
import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.exception.UserNotFoundException;
import com.codemari.timezonefollowerrest.model.Contact;
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

    public UserService() {
    }

    public AppUserDto addUser(AppUserDto userDto) {
        AppUser user = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if (user == null || !user.getIsActive()) {


            if (user == null) {
                user = new AppUser()
                        .setName(userDto.getName())
                        .setEmail(userDto.getEmail())
                        .setPhoneNumber(userDto.getPhoneNumber())
                        .setIsActive(true);
            } else {
                user
                        .setName(userDto.getName())
                        .setEmail(userDto.getEmail())
                        .setIsActive(true);
            }

            return ModelToDto.toAppUserDto(userRepository.save(user));
        }

        throw new DuplicatedUserException(userDto.getPhoneNumber());
    }

    @Transactional
    public AppUserDto findUserByPhoneNumber(String phoneNumber) {
        AppUser appUser = userRepository.findByPhoneNumber(phoneNumber);
        if (appUser != null) {
            return ModelToDto.toAppUserDto(appUser);
        }

        throw new UserNotFoundException(phoneNumber);
    }

    public AppUserDto findUserById(Long id) {
        Optional<AppUser> appUser = userRepository.findById(id);
        if (appUser.isPresent()) {
            return ModelToDto.toAppUserDto(appUser.get());
        }

        throw new UserNotFoundException(String.valueOf(id));
    }

    public AppUserDto updateUser(AppUserDto userDtoUpdated) {
        Optional<AppUser> user = Optional.ofNullable(userRepository.findByPhoneNumber(userDtoUpdated.getPhoneNumber()));

        if (user.isPresent()) {
            AppUser userModel = user.get();
            userModel
                    .setName(userDtoUpdated.getName())
                    .setEmail(userDtoUpdated.getEmail())
                    .setPhoneNumber(userDtoUpdated.getPhoneNumber());

            userRepository.save(userModel);

            return ModelToDto.toAppUserDto(userModel);
        }

        throw new UserNotFoundException(userDtoUpdated.getPhoneNumber());
    }

    public List<AppUserDto> updateUserContacts(AppUserDto appUserDto, List<String> contacts) {
        Optional<AppUser> user = Optional.ofNullable(userRepository.findByPhoneNumber(appUserDto.getPhoneNumber()));
        List<AppUserDto> contactList = new ArrayList<>();
        if (user.isPresent()) {
            for (String number : contacts) {
                AppUser contactAppUser = userRepository.findByPhoneNumber(number);
                if (contactAppUser == null) {
                    contactAppUser = new AppUser().setPhoneNumber(number).setIsActive(false);
                    userRepository.save(contactAppUser);
                }

                Contact contact = contactRepository.findByContactIdAndMainUserId(contactAppUser.getId(), user.get().getId());

                if (contact == null) {
                    contact = new Contact().setContactId(contactAppUser.getId()).setMainUserId(user.get().getId());
                }
                contactRepository.save(contact);
                contactList.add(ModelToDto.toAppUserDto(contactAppUser));
            }

            return contactList;
        }

        throw new UserNotFoundException(appUserDto.getPhoneNumber());
    }

    public List<AppUserDto> getUserContacts(AppUserDto appUserDto) {
        Optional<AppUser> user = Optional.ofNullable(userRepository.findByPhoneNumber(appUserDto.getPhoneNumber()));
        List<AppUserDto> contactList = new ArrayList<>();

        if (user.isPresent()) {
            List<Contact> contacts = user.get().getContacts();

            for (Contact contact : contacts) {
                Optional<AppUser> contactUser = userRepository.findById(contact.getContactId());

                contactUser.ifPresent(appUser -> contactList.add(ModelToDto.toAppUserDto(contactUser.get())));
            }

            return contactList;
        }

        throw new UserNotFoundException(appUserDto.getPhoneNumber());
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
