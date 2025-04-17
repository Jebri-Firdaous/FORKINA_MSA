package com.example.serviceprojecthamzambarki.service;

import com.example.serviceprojecthamzambarki.dto.ProjectWithUserDTO;
import com.example.serviceprojecthamzambarki.dto.UserDTO;
import com.example.serviceprojecthamzambarki.entity.Project;
import com.example.serviceprojecthamzambarki.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IProjectService interface with project management logic,
 * user integration via user-management service using WebClient, email notifications, and logging.
 */
@Service
@Slf4j
public class ProjectServiceImpl implements IProjectService {

    private final ProjectRepository projectRepository;
    private final WebClient webClient;
    private final EmailService emailService;

    /**
     * Constructor-based dependency injection for required components.
     */
    public ProjectServiceImpl(ProjectRepository projectRepository, WebClient webClient, EmailService emailService) {
        this.projectRepository = projectRepository;
        this.webClient = webClient;
        this.emailService = emailService;
    }

    /**
     * Retrieves all projects from the database.
     */
    @Override
    public List<Project> retrieveAllProjects() {
        log.info("Retrieving all projects");
        return projectRepository.findAll();
    }

    /**
     * Retrieves all projects with their associated user details.
     */
    @Override
    public List<ProjectWithUserDTO> retrieveAllProjectsWithUsers() {
        log.info("Retrieving all projects with user details");
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> {
                    UserDTO user = null;
                    if (project.getUserId() != null) {
                        user = fetchUserFromUserService(project.getUserId());
                    }
                    return new ProjectWithUserDTO(project, user);
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single project by its ID.
     */
    @Override
    public Project retrieveProject(Integer id) {
        log.info("Retrieving project with ID: {}", id);
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project with ID " + id + " not found"));
    }

    /**
     * Retrieves a single project with its associated user details by ID.
     */
    @Override
    public ProjectWithUserDTO retrieveProjectWithUser(Integer id) {
        log.info("Retrieving project with user details for ID: {}", id);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project with ID " + id + " not found"));

        UserDTO user = null;
        if (project.getUserId() != null) {
            user = fetchUserFromUserService(project.getUserId());
        }

        return new ProjectWithUserDTO(project, user);
    }

    /**
     * Adds a new project to the database.
     */
    @Override
    public Project addProject(Project project) {
        log.info("Adding new project: {}", project.getTitle());
        return projectRepository.save(project);
    }

    /**
     * Removes a project by its ID.
     */
    @Override
    public void removeProject(Integer id) {
        log.info("Removing project with ID: {}", id);
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project with ID " + id + " not found");
        }
        projectRepository.deleteById(id);
    }

    /**
     * Modifies an existing project.
     */
    @Override
    public Project modifyProject(Project project) {
        log.info("Modifying project with ID: {}", project.getIdProject());
        if (!projectRepository.existsById(project.getIdProject())) {
            throw new RuntimeException("Project with ID " + project.getIdProject() + " not found");
        }
        return projectRepository.save(project);
    }

    /**
     * Assigns a user to a project and sends an email notification.
     */
    @Override
    public Project assignProjectToUser(Integer projectId, Integer userId) {
        log.info("Assigning user ID {} to project ID {}", userId, projectId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project with ID " + projectId + " not found"));

        UserDTO user = fetchUserFromUserService(userId);
        if (user == null) {
            throw new RuntimeException("User with ID " + userId + " not found");
        }

        project.setUserId(user.getIdUser());
        Project updatedProject = projectRepository.save(project);

        // Send email notification asynchronously
        try {
            emailService.sendAssignmentEmail(
                    user.getEmail(),
                    user.getFirstName() + " " + user.getLastName(),
                    project.getTitle(),
                    project.getIdProject()
            );
            log.info("Email sending triggered for {} for project assignment: {}", user.getEmail(), project.getTitle());
        } catch (Exception e) {
            log.error("Failed to trigger email sending for {}: {}", user.getEmail(), e.getMessage());
        }

        return updatedProject;
    }

    /**
     * Finds all projects assigned to a specific user by their ID.
     */
    @Override
    public List<Project> findProjectsByUserId(Integer userId) {
        log.info("Finding projects for user ID: {}", userId);
        return projectRepository.findByUserId(userId);
    }

    /**
     * Fetches user details from the user-management service using WebClient.
     */
    private UserDTO fetchUserFromUserService(Integer userId) {
        try {
            log.info("Fetching user details for ID {} from user-management service", userId);
            Mono<UserDTO> userMono = WebClient.builder()
                    .baseUrl("http://localhost:8086")
                    .build()
                    .get()
                    .uri("/users/" + userId)
                    .retrieve()
                    .bodyToMono(UserDTO.class);
            UserDTO user = userMono.block();
            if (user == null) {
                log.warn("User with ID {} not found", userId);
            } else {
                log.info("Fetched user: {}", user.getEmail());
            }
            return user;
        } catch (Exception e) {
            log.error("Error fetching user with ID {}: {}", userId, e.getMessage(), e);
            throw e;
        }
    }
}