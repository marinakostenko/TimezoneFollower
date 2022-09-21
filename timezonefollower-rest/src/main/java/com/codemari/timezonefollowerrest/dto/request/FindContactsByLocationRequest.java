package com.codemari.timezonefollowerrest.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record FindContactsByLocationRequest(
        @NotNull Long locationId,
        @NotNull @Email String email,
        @NotNull @Pattern(regexp="(^$|\\d{10})") String phoneNumber
) {
    public FindContactsByLocationRequest() {
        this(null, null, null);
    }
}