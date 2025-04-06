package tn.esprit.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@EnableDiscoveryClient
@SpringBootApplication

public class TaskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApplication.class, args);
    }
    @Autowired
    private TaskRepository repository;
    @Bean
    ApplicationRunner init()
    {
        return  (args)->
        {
            repository.save(new Task("Login", "Créer une page login", Status.IN_PROGRESS, LocalDateTime.parse("2025-01-25T15:30:00")));
            repository.save(new Task("Register", "Créer une page Register", Status.COMPLETED, LocalDateTime.parse("2025-01-30T08:30:00")));
            repository.save(new Task("Profile", "Créer une page Profile", Status.PENDING, LocalDateTime.parse("2025-02-02T23:40:00")));
            repository.save(new Task("Liste de task", "Créer une page qui affiche les tasks", Status.ARCHIVED, LocalDateTime.parse("2025-02-15T15:59:00")));
            repository.save(new Task("forgot Password", "Créer une page de ForgotPassword", Status.IN_PROGRESS, LocalDateTime.parse("2025-02-17T00:20:00")));


// fetch
            repository.findAll().forEach(System.out::println);



        };
    }
}
