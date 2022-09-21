package com.codemari.timezonefollowerrest.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotNull @Email String username,
        String password,
        @NotNull String phoneNumber,
        String name,
        String country,
        String region,
        String timeZone,
        String city

) {
    public CreateUserRequest() {
        this(null, null, null, null, null, null, null, null);
    }
}
