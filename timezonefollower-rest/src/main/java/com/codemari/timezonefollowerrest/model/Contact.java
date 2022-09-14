package com.codemari.timezonefollowerrest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Contact {
    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_user")
    private Long contactUser;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
    @JoinColumn(name = "user_id")
    private AppUser mainUser;
}
