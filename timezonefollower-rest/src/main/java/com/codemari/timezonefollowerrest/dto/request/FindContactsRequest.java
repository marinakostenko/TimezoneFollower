package com.codemari.timezonefollowerrest.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record FindContactsRequest(
        @NotNull @Email String email,
        @NotNull String phoneNumber
) {
    public FindContactsRequest() {
        this(null, null);
    }
}
