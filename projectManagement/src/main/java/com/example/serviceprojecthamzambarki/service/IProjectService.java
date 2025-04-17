package com.example.serviceprojecthamzambarki.service;

import com.example.serviceprojecthamzambarki.dto.ProjectWithUserDTO;
import com.example.serviceprojecthamzambarki.entity.Project;

import java.util.List;

public interface IProjectService {
    List<Project> retrieveAllProjects();
    List<ProjectWithUserDTO> retrieveAllProjectsWithUsers();
    Project retrieveProject(Integer id);
    ProjectWithUserDTO retrieveProjectWithUser(Integer id);
    Project addProject(Project project);
    void removeProject(Integer id);
    Project modifyProject(Project project);
    Project assignProjectToUser(Integer projectId, Integer userId);
    List<Project> findProjectsByUserId(Integer userId);
}