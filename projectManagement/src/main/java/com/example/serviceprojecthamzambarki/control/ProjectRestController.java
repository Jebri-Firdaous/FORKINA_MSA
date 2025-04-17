package com.example.serviceprojecthamzambarki.control;

import com.example.serviceprojecthamzambarki.dto.ProjectWithUserDTO;  // Add this import
import com.example.serviceprojecthamzambarki.entity.Project;
import com.example.serviceprojecthamzambarki.service.IProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectRestController {

    private final IProjectService projectService;

    public ProjectRestController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/retrieve-all-projects")
    public List<ProjectWithUserDTO> getProjects() {
        return projectService.retrieveAllProjectsWithUsers();
    }

    @GetMapping("/retrieve-project/{id}")
    public ResponseEntity<ProjectWithUserDTO> getProject(@PathVariable Integer id) {
        try {
            ProjectWithUserDTO projectWithUser = projectService.retrieveProjectWithUser(id);
            return ResponseEntity.ok(projectWithUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add-project")
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @PutMapping("/update-project")
    public Project updateProject(@RequestBody Project project) {
        return projectService.modifyProject(project);
    }

    @DeleteMapping("/delete-project/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.removeProject(id);
    }

    @PutMapping("/assign-to-user/{projectId}/{userId}")
    public ResponseEntity<Project> assignProjectToUser(@PathVariable Integer projectId, @PathVariable Integer userId) {
        try {
            Project updatedProject = projectService.assignProjectToUser(projectId, userId);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}