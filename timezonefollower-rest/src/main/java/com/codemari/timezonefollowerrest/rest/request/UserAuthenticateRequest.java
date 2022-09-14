package com.codemari.timezonefollowerrest.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthenticateRequest {
    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNumber;

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
