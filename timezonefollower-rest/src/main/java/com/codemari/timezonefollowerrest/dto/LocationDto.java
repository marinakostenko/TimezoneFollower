package com.codemari.timezonefollowerrest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LocationDto {
    Long id;
    String city;
    String country;
    String timezone;
    String countryCode;
    Double lat;
    Double lon;
}
