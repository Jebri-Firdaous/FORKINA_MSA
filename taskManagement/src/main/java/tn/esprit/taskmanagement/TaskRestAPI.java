package tn.esprit.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskRestAPI {
    @Autowired
    private TaskService service;


    // Afficher tous les tasks
    @GetMapping("/all")
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        return new ResponseEntity<>(service.afficherTous(), HttpStatus.OK);
    }

    // Afficher un task par ID
    @GetMapping("/{id:\\d+}")
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


    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") int id,
                                                   @RequestBody Task task){
        return new ResponseEntity<>(service.updateTask(id, task),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteTask(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(service.deleteTask(id), HttpStatus.OK);
    }
}
