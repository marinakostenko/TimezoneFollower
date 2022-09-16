package com.codemari.timezonefollowerrest.config;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class SwaggerController {
    @ApiOperation("login")
    @PostMapping("/auth")
    public void swaggerLogin(@RequestBody @Valid LoginRequest loginRequest) {
        throw new IllegalStateException("Method for swagger only");
    }

    @ApiOperation("Logout")
    @PostMapping("/logout")
    public void swaggerLogout() {
        throw new IllegalStateException("Method for swagger only");
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    private static class LoginRequest {
        @NotNull(message = "{constraints.NotEmpty.message}")
        private String email;
        @NotNull(message = "{constraints.NotEmpty.message}")
        private String proneNumber;
    }
}
