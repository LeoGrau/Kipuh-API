package com.nastypad.kipuhapi.security.domain.service;

import com.nastypad.kipuhapi.security.domain.model.entity.User;
import com.nastypad.kipuhapi.security.domain.service.communication.request.AuthRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.request.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    //ResponseEntity<?> authenticate(AuthRequest request);
    ResponseEntity<?> register(RegisterRequest request);
    List<User> getAll();
    User findByUserId(String username);
}
