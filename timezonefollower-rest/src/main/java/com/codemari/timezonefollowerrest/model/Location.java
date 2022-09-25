package com.codemari.timezonefollowerrest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Location {
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String countryCode;

    @Column
    private String timezone;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private Integer population;

    @OneToMany(mappedBy = "location")
    private List<AppUser> users;
}
