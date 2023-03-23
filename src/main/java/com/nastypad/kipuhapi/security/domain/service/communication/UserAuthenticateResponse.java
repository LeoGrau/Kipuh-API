package com.nastypad.kipuhapi.security.domain.service.communication;

import com.nastypad.kipuhapi.security.resource.UserAuthResource;
import com.nastypad.kipuhapi.shared.domain.service.communication.BaseResponse;

public class UserAuthenticateResponse extends BaseResponse<UserAuthResource> {
    public UserAuthenticateResponse(UserAuthResource resource) {
        super(resource);
    }

    public UserAuthenticateResponse(String message) {
        super(message);
    }
}
