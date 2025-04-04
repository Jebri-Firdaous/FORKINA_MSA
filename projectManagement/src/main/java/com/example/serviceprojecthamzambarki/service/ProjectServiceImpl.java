package com.example.serviceprojecthamzambarki.service;

import com.example.serviceprojecthamzambarki.entity.Project;
import com.example.serviceprojecthamzambarki.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService {

    private final ProjectRepository projectRepository;
    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    @Override
    public List<Project> retrieveAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project retrieveProject(Integer id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void removeProject(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project modifyProject(Project project) {
        return projectRepository.save(project);
    }
}