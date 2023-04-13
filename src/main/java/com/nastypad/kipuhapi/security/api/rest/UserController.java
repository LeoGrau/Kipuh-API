package com.nastypad.kipuhapi.security.api.rest;

import com.nastypad.kipuhapi.security.domain.service.AuthService;
import com.nastypad.kipuhapi.security.domain.service.RegisterService;
import com.nastypad.kipuhapi.security.domain.service.UserService;
import com.nastypad.kipuhapi.security.domain.service.communication.request.AuthRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.request.RegisterRequest;
import com.nastypad.kipuhapi.security.mapping.UserMapper;
import com.nastypad.kipuhapi.security.resources.show.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final RegisterService registerService;
    private final UserMapper mapper;

    public UserController(UserService userService, AuthService authService, RegisterService registerService, UserMapper mapper) {
        this.userService = userService;
        this.authService = authService;
        this.registerService = registerService;
        this.mapper = mapper;
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(RegisterRequest request) {
        return registerService.register(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        List<UserResource> resources = mapper.toListResource(userService.getAll());
        return ResponseEntity.ok(resources);
    }
    @GetMapping("{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getByUserId(@PathVariable long userId) {
        UserResource resources = mapper.toResource(userService.findByUserId(userId));
        return ResponseEntity.ok(resources);
    }
}
