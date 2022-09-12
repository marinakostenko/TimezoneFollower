package com.codemari.timezonefollowerrest.dto;

import com.codemari.timezonefollowerrest.model.Location;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AppUserDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private Boolean isActive;
    private String city;
    private String country;
    private String timeZone;
    private Set<ContactDto> contacts;
}
