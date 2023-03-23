package com.nastypad.kipuhapi.security.domain.service.communication;

import com.nastypad.kipuhapi.security.resource.UserResource;
import com.nastypad.kipuhapi.shared.domain.service.communication.BaseResponse;

public class UserRegisterResponse extends BaseResponse<UserResource> {
    public UserRegisterResponse(UserResource resource) {
        super(resource);
    }

    public UserRegisterResponse(String message) {
        super(message);
    }
}
