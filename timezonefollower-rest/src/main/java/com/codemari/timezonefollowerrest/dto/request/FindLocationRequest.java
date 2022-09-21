package com.codemari.timezonefollowerrest.dto.request;

import javax.validation.constraints.NotNull;

public record FindLocationRequest(
        @NotNull String city,
        @NotNull String country,
        @NotNull String region
) {
    public FindLocationRequest() {
        this(null, null, null);
    }
}