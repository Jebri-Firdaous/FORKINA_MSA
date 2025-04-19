package com.example.serviceprojecthamzambarki.dto;

import com.example.serviceprojecthamzambarki.entity.Project;
import lombok.Data;

@Data
public class ProjectWithUserDTO {
    private Project project;
    private UserDTO user;

    public ProjectWithUserDTO(Project project, UserDTO user) {
        this.project = project;
        this.user = user;
    }
}