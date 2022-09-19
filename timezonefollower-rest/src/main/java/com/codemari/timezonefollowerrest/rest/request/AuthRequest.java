package com.codemari.timezonefollowerrest.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record AuthRequest(
        @NotNull @Email String username,
        @NotNull String password
) {
    public AuthRequest() {
        this(null, null);
    }
}
