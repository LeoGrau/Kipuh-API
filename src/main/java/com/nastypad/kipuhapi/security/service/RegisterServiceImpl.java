package com.nastypad.kipuhapi.security.service;

import com.nastypad.kipuhapi.security.domain.service.RegisterService;
import com.nastypad.kipuhapi.security.domain.service.communication.request.RegisterRequest;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final AuthenticationManager authenticationManager;
    private final EnhancedModelMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        return null;
    }
}
