package com.codemari.timezonefollowerrest.dao;

import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByContactUserAndMainUser(Long contactUser, AppUser mainUser);
}
