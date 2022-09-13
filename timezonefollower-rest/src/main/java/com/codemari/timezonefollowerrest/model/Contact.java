package com.codemari.timezonefollowerrest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "contact")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Contact {
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "main_user_id")
    private Long mainUserId;

    @ManyToMany(mappedBy = "contacts")
    private Collection<AppUser> users;
}
