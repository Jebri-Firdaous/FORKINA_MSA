package com.example.serviceprojecthamzambarki.service;

import com.example.serviceprojecthamzambarki.entity.Project;
import java.util.List;

public interface IProjectService {
    List<Project> retrieveAllProjects();
    Project retrieveProject(Integer id);
    Project addProject(Project project);
    void removeProject(Integer id);
    Project modifyProject(Project project);
}
