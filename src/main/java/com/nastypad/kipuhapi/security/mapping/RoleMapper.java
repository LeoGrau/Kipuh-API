package com.nastypad.kipuhapi.security.mapping;

import com.nastypad.kipuhapi.security.domain.model.entity.Role;
import com.nastypad.kipuhapi.security.domain.model.enums.Roles;
import com.nastypad.kipuhapi.security.resources.show.RoleResource;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class RoleMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Roles, String> rolesToString = new AbstractConverter<Roles, String>() {

        @Override
        protected String convert(Roles role) {
            return role == null? null : role.name();
        }
    };

    Converter<Role, String> roleToString = new AbstractConverter<Role, String>() {
        @Override
        protected String convert(Role role) {
            return role == null? null : role.getRoleName().name();
        }
    };

    public RoleResource toResource(Role model) {
        return mapper.map(model, RoleResource.class);
    }

    public List<RoleResource> toListResource(List<Role> listModel) {
        mapper.addConverter(roleToString);
        return mapper.mapList(listModel, RoleResource.class);
    }
}
