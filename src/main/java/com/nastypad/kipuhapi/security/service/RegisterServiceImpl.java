package com.nastypad.kipuhapi.security.service;

import com.nastypad.kipuhapi.security.domain.model.entity.Role;
import com.nastypad.kipuhapi.security.domain.model.entity.User;
import com.nastypad.kipuhapi.security.domain.model.enums.Roles;
import com.nastypad.kipuhapi.security.domain.persistence.RoleRepository;
import com.nastypad.kipuhapi.security.domain.persistence.UserRepository;
import com.nastypad.kipuhapi.security.domain.service.RegisterService;
import com.nastypad.kipuhapi.security.domain.service.communication.request.RegisterRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.response.AuthResponse;
import com.nastypad.kipuhapi.security.domain.service.communication.response.RegisterResponse;
import com.nastypad.kipuhapi.security.resources.show.UserResource;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final AuthenticationManager authenticationManager;
    private final EnhancedModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            AuthResponse authResponse = new AuthResponse("Username is already used");
            return ResponseEntity.badRequest().body(authResponse.getMessage());
        }
        try {
            Set<String> roleStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();
            if(roleStringSet == null) {
                roleRepository.findByRoleName(Roles.ROLE_USER).map(roles::add).orElseThrow(() -> new RuntimeException("Role not found"));
            }
            logger.info("Roles: {}", roles);
            User user = new User()
                    .withUsername(request.getUsername())
                    .withEmail(request.getEmail())
                    .withPassword(encoder.encode(request.getPassword()))
                    .withRoles(roles);
            userRepository.save(user);
            UserResource userResource = mapper.map(user, UserResource.class);
            RegisterResponse registerResponse = new RegisterResponse(userResource);
            return ResponseEntity.ok(registerResponse.getMessage());
        } catch (Exception exception) {
            RegisterResponse registerResponse = new RegisterResponse(exception.getMessage());
            return ResponseEntity.badRequest().body(registerResponse.getMessage());
        }
    }
}
