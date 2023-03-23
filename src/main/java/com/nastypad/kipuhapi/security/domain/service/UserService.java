package com.nastypad.kipuhapi.security.domain.service;

import com.nastypad.kipuhapi.security.domain.model.entity.User;
import com.nastypad.kipuhapi.security.domain.service.communication.UserAuthenticateRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getById(Long id);
    ResponseEntity<?> authenticate(UserAuthenticateRequest authenticateRequest);
    ResponseEntity<?> register(UserRegisterRequest registerRequest);
}
