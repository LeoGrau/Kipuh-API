package com.nastypad.kipuhapi.security.mapping;

import com.nastypad.kipuhapi.security.domain.model.entity.Role;
import com.nastypad.kipuhapi.security.resources.show.UserResource;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Role, String> roleStringConverter = new AbstractConverter<Role, String>() {
        @Override
        protected String convert(Role role) {
            return role == null? null : role.getRoleName().name();
        }
    };

    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }

   public List<UserResource> toListResource(List<User> listModel) {
        return mapper.mapList(listModel, UserResource.class);
   }
}
