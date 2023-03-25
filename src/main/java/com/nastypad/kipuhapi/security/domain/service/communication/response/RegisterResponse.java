package com.nastypad.kipuhapi.security.domain.service.communication.response;

import com.nastypad.kipuhapi.security.resources.show.UserResource;
import com.nastypad.kipuhapi.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(UserResource resource) {
        super(resource);
    }

    public RegisterResponse(String message) {
        super(message);
    }
}
