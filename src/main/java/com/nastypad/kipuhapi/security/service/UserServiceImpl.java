package com.nastypad.kipuhapi.security.service;

import com.nastypad.kipuhapi.security.domain.model.entity.User;
import com.nastypad.kipuhapi.security.domain.persistence.UserRepository;
import com.nastypad.kipuhapi.security.domain.service.UserService;
import com.nastypad.kipuhapi.security.domain.service.communication.request.AuthRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.request.RegisterRequest;
import com.nastypad.kipuhapi.security.domain.service.communication.response.AuthResponse;
import com.nastypad.kipuhapi.security.middleware.UserDetailsImpl;
import com.nastypad.kipuhapi.security.resources.auth.AuthResource;
import com.nastypad.kipuhapi.shared.exception.ResourceNotFoundException;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnhancedModelMapper mapper;



    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserId(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Not found!"));
    }

    //The only thing we need for login is this.
    // Also, it serves for AuthenticationManager and its authenticate method to find user (AuthServiceImpl)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }

}
