package com.nastypad.kipuhapi.security.service;

import com.nastypad.kipuhapi.security.domain.service.AuthService;
import com.nastypad.kipuhapi.security.domain.service.communication.request.AuthRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.response.AuthResponse;
import com.nastypad.kipuhapi.security.middleware.UserDetailsImpl;
import com.nastypad.kipuhapi.security.resources.auth.AuthResource;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final EnhancedModelMapper mapper;

    @Override
    public ResponseEntity<?> authenticate(AuthRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            AuthResource authResource = mapper.map(userDetails, AuthResource.class);
            AuthResponse response = new AuthResponse(authResource);
            return ResponseEntity.ok(response.getResource());

        } catch(Exception exception) {
            AuthResponse response = new AuthResponse(
                    String.format("An error occurred while authenticating: %s", exception.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }
//        return null;
    }
}
