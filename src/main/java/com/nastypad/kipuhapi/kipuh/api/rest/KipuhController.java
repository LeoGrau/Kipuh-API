package com.nastypad.kipuhapi.kipuh.api.rest;

import com.nastypad.kipuhapi.shared.configuration.Project;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class KipuhController {
    @GetMapping
    public Project getProjectInfo() {
        return new Project(
                "Leonardo Grau",
                "Kipuh! is great! 💙",
                "https://github.com/LeoGrau",
                "Kipuh!"
        );
    }
}
