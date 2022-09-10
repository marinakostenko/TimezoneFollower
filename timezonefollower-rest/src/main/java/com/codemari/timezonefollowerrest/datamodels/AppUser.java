package com.codemari.timezonefollowerrest.datamodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class AppUser {
    private @Id @GeneratedValue Long id;
    private String name;
    private String location;
    private String phoneNumber;
    private String timeZone;
//    @ElementCollection(targetClass=String.class)
//    private List<String> cities;

    public AppUser() {}

    public AppUser(String name, String location, String phoneNumber, String timeZone) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.timeZone = timeZone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser appUser)) return false;
        return getId().equals(appUser.getId()) && getName().equals(appUser.getName()) && getLocation().equals(appUser.getLocation()) && getPhoneNumber().equals(appUser.getPhoneNumber()) && getTimeZone().equals(appUser.getTimeZone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLocation(), getPhoneNumber(), getTimeZone());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", timeZone='" + timeZone +
                '}';
    }
}
