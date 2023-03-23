package com.nastypad.kipuhapi.security.mapping;

import com.nastypad.kipuhapi.security.mapping.mappers.RoleMapper;
import com.nastypad.kipuhapi.security.mapping.mappers.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    UserMapper userMapper() {
         return new UserMapper();
    }

    @Bean
    RoleMapper roleMapper() {
        return new RoleMapper();
    }
}
