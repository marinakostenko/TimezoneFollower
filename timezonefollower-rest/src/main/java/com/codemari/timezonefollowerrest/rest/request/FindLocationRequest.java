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
public class FindLocationRequest {
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
    @NotEmpty
    private String region;
}
