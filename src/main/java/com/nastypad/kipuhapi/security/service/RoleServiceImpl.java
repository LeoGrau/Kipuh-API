package com.nastypad.kipuhapi.security.service;

import com.nastypad.kipuhapi.security.domain.model.entity.Role;
import com.nastypad.kipuhapi.security.domain.model.enums.Roles;
import com.nastypad.kipuhapi.security.domain.persistence.RoleRepository;
import com.nastypad.kipuhapi.security.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void seed() {
        List<String> roleStrings = Arrays.asList(Roles.values()).stream().map(Enum::name).toList(); //Enum::name is the same as role -> role.name()
        roleStrings.forEach(roleString -> {
            Roles roleName = Roles.valueOf(roleString);
            if(!roleRepository.existsByRoleName(roleName)) {
                roleRepository.save(new Role().withRoleName(roleName));
            }
        });
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
