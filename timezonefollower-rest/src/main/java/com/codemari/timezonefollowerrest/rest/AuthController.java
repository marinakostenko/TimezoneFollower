package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.ModelToDto;
import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.rest.request.AuthRequest;
import com.codemari.timezonefollowerrest.rest.request.CreateUserRequest;
import com.codemari.timezonefollowerrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    @Autowired
    private UserService userService;

    public ResponseEntity<AppUserDto> login(@RequestBody @Valid AuthRequest authRequest) {
        var authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));


        AppUser user = (AppUser) authentication.getPrincipal();

        var now = Instant.now();
        var expiry = 36000L;

        var scope =
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(joining(" "));

        var claims =
                JwtClaimsSet.builder()
                        .issuer("example.io")
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(expiry))
                        .subject(format("%s,%s", user.getId(), user.getUsername()))
                        .claim("roles", scope)
                        .build();

        var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(ModelToDto.toAppUserDto(user));
    }

    @PostMapping("register")
    public AppUserDto register(@RequestBody @Valid CreateUserRequest createUserRequest) {
        AppUserDto userDto = new AppUserDto()
                .setEmail(createUserRequest.username())
                .setName(createUserRequest.name() == null ? "user name" : createUserRequest.name())
                .setPhoneNumber(createUserRequest.phoneNumber())
                .setCity(createUserRequest.city())
                .setCountry(createUserRequest.country())
                .setRegion(createUserRequest.region())
                .setTimeZone(createUserRequest.timeZone());
        return this.userService.addUser(userDto);
    }
}
