package com.nastypad.kipuhapi.security.resources.auth;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthResource {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String token;
}
