package com.codemari.timezonefollowerrest.dao;

import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByContactIdAndMainUserId(Long contactId, Long mainUserId);
}
