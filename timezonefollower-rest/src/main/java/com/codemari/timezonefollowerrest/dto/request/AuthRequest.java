package com.codemari.timezonefollowerrest.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record AuthRequest(
        @NotNull @Email String username,
        @NotNull @Pattern(regexp="(^$|\\d{10})") String password
) {
    public AuthRequest() {
        this(null, null);
    }
}
