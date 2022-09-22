package com.codemari.timezonefollowerrest.dto.request;

import javax.validation.constraints.NotNull;
import java.util.List;


public record UpdateUserContactsRequest(
        @NotNull String userId,
        @NotNull List<String> contacts
) {
    public UpdateUserContactsRequest() {
        this(null, null);
    }
}
