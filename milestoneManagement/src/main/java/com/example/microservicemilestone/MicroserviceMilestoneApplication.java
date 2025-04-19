package com.example.microservicemilestone;

import com.example.microservicemilestone.model.Milestone;
import com.example.microservicemilestone.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceMilestoneApplication {
    @Autowired
    private MilestoneRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMilestoneApplication.class, args);
    }
    @Bean
    ApplicationRunner init() {
        return args -> {
            // Création de 5 instances de Milestone
            repository.save(new Milestone("Milestone 1", "Description of milestone 1", LocalDateTime.parse("2025-03-20T10:00:00"), 1, 101));
            repository.save(new Milestone("Milestone 2", "Description of milestone 2", LocalDateTime.parse("2025-04-10T15:30:00"), 1, 102));
            repository.save(new Milestone("Milestone 3", "Description of milestone 3", LocalDateTime.parse("2025-05-05T09:45:00"), 2, 103));
            repository.save(new Milestone("Milestone 4", "Description of milestone 4", LocalDateTime.parse("2025-06-15T13:20:00"), 3, 104));
            repository.save(new Milestone("Milestone 5", "Description of milestone 5", LocalDateTime.parse("2025-07-01T16:00:00"), 4, 105));

            // Affichage des milestones enregistrées
            repository.findAll().forEach(System.out::println);
        };
    }
}
