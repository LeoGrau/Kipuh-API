package com.nastypad.kipuhapi.security.domain.service;

import com.nastypad.kipuhapi.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {
    void seed();
    List<Role> getAll();
}
