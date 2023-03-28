package com.nastypad.kipuhapi.security.domain.service;

import com.nastypad.kipuhapi.security.domain.service.communication.request.AuthRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticate(AuthRequest request);
}
