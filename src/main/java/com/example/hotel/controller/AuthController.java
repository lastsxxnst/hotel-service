package com.example.hotel.controller;

import com.example.hotel.model.User;
import com.example.hotel.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller responsible for authentication (login & registration).
 *
 * <p>
 * Provides endpoints for user login and user registration.
 * </p>
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User request) {

        User user = authService.login(request.getUsername(), request.getPassword());
        return Map.of(
                "token", "mock-token-" + user.getId(),
                "role", user.getRole()
        );
    }
    /**
     * Authenticates user and returns a simple token.
     *
     * @param request user credentials
     * @return map containing token and role
     */
    @PostMapping("/register")
    public User register(@RequestBody User request) {
        return authService.register(request);
    }
}