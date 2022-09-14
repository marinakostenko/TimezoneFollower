package com.codemari.timezonefollowerrest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AppUser {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String email;
    @Column()
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "active_user")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "mainUser")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "appUser")
    private List<FavouriteLocation> favouriteLocations;


}
