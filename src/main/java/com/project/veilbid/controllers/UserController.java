package com.project.veilbid.controllers;

import com.project.veilbid.domain.dto.UserProfileDTO;
import com.project.veilbid.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserProfileDTO getMe(
            JwtAuthenticationToken token
    ) {

        UUID userId = UUID.fromString(
                token.getToken().getSubject()
        );

        return userService.getCurrentUser(userId);
    }

    @PutMapping("/me")
    public UserProfileDTO updateMe(
            JwtAuthenticationToken token,
            @RequestBody UserProfileDTO dto
    ) {

        UUID userId = UUID.fromString(
                token.getToken().getSubject()
        );

        return userService.updateCurrentUser(
                userId,
                dto
        );
    }
}