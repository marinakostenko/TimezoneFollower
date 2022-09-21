package com.codemari.timezonefollowerrest.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record CreateUserRequest(
        @NotNull @Email String email,
        @NotNull @Pattern(regexp="(^$|\\d{10})") String phoneNumber,
        String name,
        String country,
        String region,
        String timeZone,
        String city

) {
    public CreateUserRequest() {
        this(null, null, null, null, null, null, null);
    }
}
