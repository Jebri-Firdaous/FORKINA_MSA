package com.example.serviceprojecthamzambarki;

import com.example.serviceprojecthamzambarki.entity.Project;
import com.example.serviceprojecthamzambarki.entity.Status;
import com.example.serviceprojecthamzambarki.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceProjecthamzaMbarkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProjecthamzaMbarkiApplication.class, args);
    }
    @Autowired
    private ProjectRepository projectRepository;

    @Bean
    ApplicationRunner init() {
        return (args) -> {
            projectRepository.save(new Project(null, "Project Alpha", "Develop the alpha version of the product", new Date(), Status.IN_PROGRESS, Arrays.asList("High Priority", "Feature"), 101));
            projectRepository.save(new Project(null, "Project Beta", "Finalize the beta version", new Date(), Status.PENDING, Arrays.asList("Medium Priority", "Feature"), 102));
            projectRepository.save(new Project(null, "Project Gamma", "Test the gamma version", new Date(), Status.COMPLETED, Arrays.asList("Low Priority", "Test"), 103));
            projectRepository.save(new Project(null, "Project Delta", "Prepare for the delta version launch", new Date(), Status.ARCHIVED, Arrays.asList("High Priority", "Launch"), 104));

            // Fetch all projects and log them to the console
            projectRepository.findAll().forEach(System.out::println);
        };
    }
}
