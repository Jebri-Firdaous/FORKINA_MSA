package tn.esprit.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repo;

    /*Afficher tous les tasks*/
    public Iterable<Task> afficherTous() {
        return repo.findAll();
    }

    /*Afficher une task par son ID*/
    public Task afficherParId(int id) {
        return repo.findById(id).orElse(null);
    }
    /*Ajout */
    public Task addTask(Task task) {
        return repo.save(task);
    }
    public Task updateTask(int id, Task newTask) {
        if (repo.findById(id).isPresent()) {
            Task existingTask = repo.findById(id).get();
            existingTask.setTaskName(newTask.getTaskName());
            existingTask.setDescription(newTask.getDescription());
            existingTask.setStatus(newTask.getStatus());
            existingTask.setDueDate(newTask.getDueDate());

            return repo.save(existingTask);
        } else
            return null;
    }
    public String deleteTask(int id) {
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
            return "task supprimé";
        } else
            return "task non supprimé";
    }
}
