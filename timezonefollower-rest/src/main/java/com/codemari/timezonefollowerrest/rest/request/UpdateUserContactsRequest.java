package com.codemari.timezonefollowerrest.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserContactsRequest {
    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private List<String> contacts;

}
