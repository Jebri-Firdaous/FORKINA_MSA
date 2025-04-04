package com.example.serviceprojecthamzambarki.control;

import com.example.serviceprojecthamzambarki.entity.Project;
import com.example.serviceprojecthamzambarki.service.IProjectService;
import lombok.AllArgsConstructor;
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
    public List<Project> getProjects() {
        return projectService.retrieveAllProjects();
    }

    @GetMapping("/retrieve-project/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Integer id) {
        Project project = projectService.retrieveProject(id);
        return project != null ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
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
}
