package com.codemari.timezonefollowerrest.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindContactsByLocationRequest {
    @NotEmpty
    private Long location_id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNumber;
}
