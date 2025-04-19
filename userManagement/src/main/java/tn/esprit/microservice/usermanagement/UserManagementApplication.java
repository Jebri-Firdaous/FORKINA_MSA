package tn.esprit.microservice.usermanagement;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @Bean
    ApplicationRunner init(UserRepository repository) {
        return args -> {
            repository.save(new User("John", "Doe", "john.doe@example.com", "password123"));
            repository.save(new User("Jane", "Smith", "jane.smith@example.com", "password123"));
            repository.save(new User("Michael", "Johnson", "michael.johnson@example.com", "password123"));
            repository.save(new User("Emily", "Davis", "emily.davis@example.com", "password123"));
            repository.save(new User("David", "Wilson", "david.wilson@example.com", "password123"));

            // fetch and print all users
            repository.findAll().forEach(System.out::println);
        };
    }
}