package com.nastypad.kipuhapi.security.mapping.mappers;

import com.nastypad.kipuhapi.security.domain.model.entity.User;
import com.nastypad.kipuhapi.security.domain.service.communication.UserRegisterRequest;
import com.nastypad.kipuhapi.security.resource.UserResource;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class UserMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }


}
