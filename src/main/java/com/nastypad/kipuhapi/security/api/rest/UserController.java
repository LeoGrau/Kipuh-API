package com.nastypad.kipuhapi.security.api.rest;

import com.nastypad.kipuhapi.security.domain.service.AuthService;
import com.nastypad.kipuhapi.security.domain.service.UserService;
import com.nastypad.kipuhapi.security.domain.service.communication.request.AuthRequest;
import com.nastypad.kipuhapi.security.mapping.UserMapper;
import com.nastypad.kipuhapi.security.service.AuthServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final UserMapper mapper;

    public UserController(UserService userService, AuthService authService, UserMapper mapper) {
        this.userService = userService;
        this.authService = authService;
        this.mapper = mapper;
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }
}
