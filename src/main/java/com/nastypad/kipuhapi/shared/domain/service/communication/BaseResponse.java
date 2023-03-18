package com.nastypad.kipuhapi.shared.domain.service.communication;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class BaseResponse<T> {
    private final boolean success;
    private final String message;
    private final T resource;

    public BaseResponse(T resource) {
        this.success = true;
        this.resource = resource;
        this.message = Strings.EMPTY;
    }

    public BaseResponse(String message) {
        this.success = false;
        this.message = message;
        this.resource = null;
    }


}
