package com.nastypad.kipuhapi.security.service;

import com.nastypad.kipuhapi.security.domain.model.entity.Role;
import com.nastypad.kipuhapi.security.domain.model.entity.User;
import com.nastypad.kipuhapi.security.domain.model.enums.RoleEnum;
import com.nastypad.kipuhapi.security.domain.repository.RoleRepository;
import com.nastypad.kipuhapi.security.domain.repository.UserRepository;
import com.nastypad.kipuhapi.security.domain.service.UserService;
import com.nastypad.kipuhapi.security.domain.service.communication.UserAuthenticateRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.UserAuthenticateResponse;
import com.nastypad.kipuhapi.security.domain.service.communication.UserRegisterRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.UserRegisterResponse;
import com.nastypad.kipuhapi.security.middleware.UserDetailsImpl;
import com.nastypad.kipuhapi.security.resource.UserResource;
import com.nastypad.kipuhapi.shared.exception.ResourceNotFoundException;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EnhancedModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User getById(Long id) {
        return userRepository.findByUserId(id).orElseThrow(() -> new ResourceNotFoundException("entity", id));
    }

    @Override
    public ResponseEntity<?> authenticate(UserAuthenticateRequest authenticateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> register(UserRegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) { //Exist by username
            UserAuthenticateResponse response = new UserAuthenticateResponse("Username is already used");
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        if (userRepository.existsByUsername(request.getEmail())) { //Exist by email
            UserAuthenticateResponse response = new UserAuthenticateResponse("Username is already used");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        try {
            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();
            if (rolesStringSet == null) { //By default, we add role user
                roleRepository.findByRole(RoleEnum.ROLE_USER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else { //We add all roles that we got from JSON.
                rolesStringSet.forEach(roleString -> roleRepository.findByRole(RoleEnum.valueOf(roleString))
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found"))
                );
            }
            User user = new User()
                    .withUsername(request.getUsername())
                    .withFirstname(request.getFirstname())
                    .withLastname(request.getLastname())
                    .withPassword(request.getPassword())
                    .withRoles(roles);
            userRepository.save(user);
            UserResource userResource = mapper.map(user, UserResource.class);
            UserRegisterResponse response = new UserRegisterResponse(userResource);
            return ResponseEntity.ok(response.getResource());
        } catch (Exception exception) {
            UserRegisterResponse response = new UserRegisterResponse(exception.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsImpl poo = new UserDetailsImpl();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }
}
