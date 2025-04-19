package tn.esprit.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskRestAPI {
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
    @Autowired
    private TaskService service;


    // Afficher tous les tasks
    @GetMapping("/all")
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        return new ResponseEntity<>(service.afficherTous(), HttpStatus.OK);
    }

    // Afficher un task par ID
    @GetMapping("/getid/{id:\\d+}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") int id) {
        Task task = service.afficherParId(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(service.addTask(task), HttpStatus.CREATED);
    }


    @PutMapping(value = "/update/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") int id,
                                                   @RequestBody Task task){
        return new ResponseEntity<>(service.updateTask(id, task),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteTask(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(service.deleteTask(id), HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<Page<Task>> filterTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = service.filterTasks(status, startDate, endDate, pageable);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @PutMapping("/updatetaskHistory/{id:\\d+}")
    public ResponseEntity<Task> updateTaskWithHistory(@PathVariable int id, @RequestBody Task newTask) {
        Task updatedTask = service.updateTasksHistory(id, newTask);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/history/{id:\\d+}")
    public ResponseEntity<?> getTaskHistory(@PathVariable int id) {
        Task task = service.afficherParId(id);
        if (task != null && task.getHistory() != null && !task.getHistory().isEmpty()) {
            return new ResponseEntity<>(task.getHistory(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Aucun historique pour cette tâche.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/history/all")
    public ResponseEntity<?> getAllTasksHistory() {
        Iterable<Task> tasksIterable = service.afficherTous(); // ça retourne un Iterable
        List<Task> tasks = new ArrayList<>();
        tasksIterable.forEach(tasks::add); // conversion Iterable -> List

        List<String> allHistory = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getHistory() != null && !task.getHistory().isEmpty()) {
                allHistory.addAll(task.getHistory());
            }
        }

        if (!allHistory.isEmpty()) {
            return new ResponseEntity<>(allHistory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Aucun historique trouvé pour les tâches.", HttpStatus.NOT_FOUND);
        }
    }




    @PatchMapping("/status/{id:\\d+}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable int id, @RequestParam Status status) {
        Task updatedTask = service.updateStatus(id, status);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
