package com.nastypad.kipuhapi.security.middleware.jwt;

import com.nastypad.kipuhapi.security.domain.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHandler jwtHandler;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private String parseTokenFrom(HttpServletRequest request) {
        String authorizationParameter = request.getHeader("Authorization");
        if(StringUtils.hasText(authorizationParameter) && authorizationParameter.startsWith("Bearer")) {
            return new LinkedList<>(Arrays.asList(authorizationParameter.split(" "))).getLast(); //token
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseTokenFrom(request);
            if(token == null || !jwtHandler.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            logger.info("Token: {}", token);
            String username = jwtHandler.getUsernameFromToken(token);
            UserDetails principal = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (Exception exception) {
            logger.error("User Authentication cannot be set: {}", exception.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
