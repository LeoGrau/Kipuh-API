package com.nastypad.kipuhapi.shared.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class Project {
    private final String authorName;
    private final String authorGithubUrl;
    private final String projectTitle;
    private final String projectDescription;

    public Project(String authorName, String projectDescription, String authorGithubUrl, String projectTitle) {
        this.authorName = authorName;
        this.projectDescription = projectDescription;
        this.authorGithubUrl = authorGithubUrl;
        this.projectTitle = projectTitle;
    }
}
