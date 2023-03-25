package com.nastypad.kipuhapi.security.mapping.config;

import com.nastypad.kipuhapi.security.mapping.RoleMapper;
import com.nastypad.kipuhapi.security.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public RoleMapper roleMapper() {
        return new RoleMapper();
    }
}
