package com.codemari.timezonefollowerrest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AppUserDto {
    private Long id;
    private String email;
    private String userName;
    private String name;
    private String password;
    private String phoneNumber;
    private Boolean isActive;
    private String city;
    private String country;
    private String region;
    private String timeZone;
}
