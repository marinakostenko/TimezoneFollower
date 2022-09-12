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

@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AppUserDto {
    private String email;
    private String name;
    private String phoneNumber;
    private Boolean isActive;
    private Collection<ContactDto> contacts;
    private LocationDto location;
}
