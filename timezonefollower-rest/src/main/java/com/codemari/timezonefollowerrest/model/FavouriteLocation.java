package com.codemari.timezonefollowerrest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "favourite_location")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class FavouriteLocation {
    @Id
    @Column(name = "favourite_location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    @Column(name = "app_user")
    private AppUser appUser;
}
