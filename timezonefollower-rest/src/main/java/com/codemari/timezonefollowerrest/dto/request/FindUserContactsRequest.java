package com.codemari.timezonefollowerrest.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record FindUserContactsRequest(
        @NotNull @Email String email,
        @NotNull @Pattern(regexp="(^$|\\d{10})") String phoneNumber
) {
    public FindUserContactsRequest() {
        this(null, null);
    }
}
