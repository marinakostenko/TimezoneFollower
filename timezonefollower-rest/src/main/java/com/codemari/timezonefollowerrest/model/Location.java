package com.codemari.timezonefollowerrest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Column()
    private String city;

    @Column()
    private String region;

    @Column()
    private String country;

    @Column
    private String timeZone;

    @OneToMany(mappedBy = "location")
    private List<AppUser> users;

}
