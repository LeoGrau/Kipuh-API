package com.nastypad.kipuhapi.security.domain.service;

import com.nastypad.kipuhapi.security.domain.service.communication.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ResponseEntity<?> register(RegisterRequest request);
}
