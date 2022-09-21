package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.db.LoadDataBase;
import com.codemari.timezonefollowerrest.dto.AppUserDto;
import com.codemari.timezonefollowerrest.dto.ModelToDto;
import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.rest.request.AuthRequest;
import com.codemari.timezonefollowerrest.rest.request.CreateUserRequest;
import com.codemari.timezonefollowerrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class AuthController {
    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<AppUserDto> login(@RequestBody @Valid AuthRequest authRequest) {
//        var authentication =
//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
//
//
//        AppUser user = (AppUser) authentication.getPrincipal();
//
//        var now = Instant.now();
//        var expiry = 36000L;
//
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
//
//        var scope =
//                authorityList.stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .collect(joining(" "));
//
//        var claims =
//                JwtClaimsSet.builder()
//                        .issuedAt(now)
//                        .issuer("codemari")
//                        .expiresAt(now.plusSeconds(expiry))
//                        .subject(format("%s,%s", user.getId(), user.getEmail()))
//                        .claim("roles", scope)
//                        .build();
//
//
//        var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//
//        log.info("token " + token);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.AUTHORIZATION, token)
//                .body(ModelToDto.toAppUserDto(user));
//    }

    @PostMapping("/register")
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
