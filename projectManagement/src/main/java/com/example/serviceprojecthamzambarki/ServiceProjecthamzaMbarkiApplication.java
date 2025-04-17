package com.example.serviceprojecthamzambarki;

import com.example.serviceprojecthamzambarki.entity.Project;
import com.example.serviceprojecthamzambarki.entity.Status;
import com.example.serviceprojecthamzambarki.repository.ProjectRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
public class ServiceProjecthamzaMbarkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProjecthamzaMbarkiApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ProjectRepository repository) {
        return args -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            repository.save(new Project(null, "Project Alpha", "Develop the alpha version of the product", dateFormat.parse("2024-12-01T10:00:00Z"), Status.IN_PROGRESS, Arrays.asList("High Priority", "Feature"), 101, null));

            repository.findAll().forEach(System.out::println);
        };
    }
}
