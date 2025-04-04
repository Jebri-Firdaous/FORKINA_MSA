package tn.esprit.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("TASK-MANAGEMENT", r -> r.path("/tasks/**")
                        .uri("lb://TASK-MANAGEMENT"))
                .route("USER-MANAGEMENT", r -> r.path("/Users/**")
                        .uri("lb://USER-MANAGEMENT"))
                .route("MILESTONE-MANAGEMENT", r -> r.path("/milestones/**")
                        .uri("lb://MILESTONE-MANAGEMENT"))
                .route("PROJECT-MANAGEMENT", r -> r.path("/projects/**")
                        .uri("lb://PROJECT-MANAGEMENT"))
                .route("FILE-MANAGEMENT", r -> r.path("/files/**")
                        .uri("lb://FILE-MANAGEMENT"))
                .build();
    }
}
