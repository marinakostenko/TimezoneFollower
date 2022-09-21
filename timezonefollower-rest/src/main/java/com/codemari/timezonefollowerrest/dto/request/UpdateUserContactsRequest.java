package com.codemari.timezonefollowerrest.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


public record UpdateUserContactsRequest(
        @NotNull @Email String email,
        @NotNull @Pattern(regexp="(^$|\\d{10})") String phoneNumber,
        @NotNull List<String> contacts
) {
    public UpdateUserContactsRequest() {
        this(null, null, null);
    }
}
