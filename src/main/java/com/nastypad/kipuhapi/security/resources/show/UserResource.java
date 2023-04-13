package com.nastypad.kipuhapi.security.resources.show;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
