package com.nastypad.kipuhapi.shared.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class CustomOpenApi extends OpenAPI {
    private final Project project;
    public CustomOpenApi(Project project) {
        super();
        this.project = project;
    }

}
