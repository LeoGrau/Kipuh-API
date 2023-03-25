package com.nastypad.kipuhapi.security.domain.service.communication.response;

import com.nastypad.kipuhapi.security.domain.service.communication.request.AuthRequest;
import com.nastypad.kipuhapi.shared.domain.service.communication.BaseResponse;

public class AuthResponse extends BaseResponse<AuthRequest> {
    public AuthResponse(AuthRequest resource) {
        super(resource);
    }

    public AuthResponse(String message) {
        super(message);
    }
}
