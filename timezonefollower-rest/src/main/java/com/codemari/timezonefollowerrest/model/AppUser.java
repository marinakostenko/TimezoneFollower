package com.codemari.timezonefollowerrest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AppUser implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String email;
    private String username;
    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
