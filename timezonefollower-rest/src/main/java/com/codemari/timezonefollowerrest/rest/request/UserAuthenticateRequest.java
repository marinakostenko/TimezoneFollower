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
public class UserAuthenticateRequest {
    @NotEmpty
    String email;

    @NotEmpty
    String phoneNumber;

    private String name;

    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
    @NotEmpty
    private String region;
    @NotEmpty
    private String timeZone;

}
