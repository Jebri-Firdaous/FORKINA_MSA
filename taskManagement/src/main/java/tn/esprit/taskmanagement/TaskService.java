package tn.esprit.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repo;

    public Iterable<Task> afficherTous() {
        return repo.findAll();
    }

    public Task afficherParId(int id) {
        return repo.findById(id).orElse(null);
    }

    public Task addTask(Task task) {
        return repo.save(task);
    }

    public Task updateTask(int id, Task newTask) {
        return repo.findById(id).map(existingTask -> {
            existingTask.setTaskName(newTask.getTaskName());
            existingTask.setDescription(newTask.getDescription());
            existingTask.setStatus(newTask.getStatus());
            existingTask.setDueDate(newTask.getDueDate());
            return repo.save(existingTask);
        }).orElse(null);
    }

    public String deleteTask(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "task supprimé";
        } else {
            return "task non supprimé";
        }
    }

    public Page<Task> filterTasks(Status status, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        if (status != null && startDate != null && endDate != null) {
            return repo.findByStatusAndDueDateBetween(status, startDate, endDate, pageable);
        } else if (status != null && startDate != null) {
            return repo.findByStatusAndDueDateAfter(status, startDate, pageable);
        } else if (status != null && endDate != null) {
            return repo.findByStatusAndDueDateBefore(status, endDate, pageable);
        } else if (status != null) {
            return repo.findByStatus(status, pageable);
        } else if (startDate != null && endDate != null) {
            return repo.findByDueDateBetween(startDate, endDate, pageable);
        } else if (startDate != null) {
            return repo.findByDueDateAfter(startDate, pageable);
        } else if (endDate != null) {
            return repo.findByDueDateBefore(endDate, pageable);
        } else {
            return repo.findAll(pageable);
        }
    }

    public Task updateTasksHistory(int id, Task newTask) {
        return repo.findById(id).map(existingTask -> {
            StringBuilder changes = new StringBuilder();
            changes.append("UpdatedAt: ").append(LocalDateTime.now()).append(" - Changes: ");

            boolean hasChanges = false;

            if (!Objects.equals(existingTask.getTaskName(), newTask.getTaskName())) {
                changes.append("TaskName changed from '").append(existingTask.getTaskName())
                        .append("' to '").append(newTask.getTaskName()).append("'; ");
                existingTask.setTaskName(newTask.getTaskName());
                hasChanges = true;
            }

            if (!Objects.equals(existingTask.getDescription(), newTask.getDescription())) {
                changes.append("Description changed from '").append(existingTask.getDescription())
                        .append("' to '").append(newTask.getDescription()).append("'; ");
                existingTask.setDescription(newTask.getDescription());
                hasChanges = true;
            }

            if (!Objects.equals(existingTask.getStatus(), newTask.getStatus())) {
                changes.append("Status changed from '").append(existingTask.getStatus())
                        .append("' to '").append(newTask.getStatus()).append("'; ");
                existingTask.setStatus(newTask.getStatus());
                hasChanges = true;
            }

            if (!Objects.equals(existingTask.getDueDate(), newTask.getDueDate())) {
                changes.append("DueDate changed from '").append(existingTask.getDueDate())
                        .append("' to '").append(newTask.getDueDate()).append("'; ");
                existingTask.setDueDate(newTask.getDueDate());
                hasChanges = true;
            }

            if (hasChanges) {
                existingTask.getHistory().add(changes.toString());
            }

            return repo.save(existingTask);
        }).orElse(null);
    }



    public Task updateStatus(int id, Status status) {
        return repo.findById(id).map(task -> {
            // Vérifie si le status est différent avant d'ajouter à l'historique
            if (!Objects.equals(task.getStatus(), status)) {
                String historyEntry = "UpdatedAt: " + LocalDateTime.now()
                        + " - Status changed from '" + task.getStatus()
                        + "' to '" + status + "'";

                task.getHistory().add(historyEntry);
            }

            task.setStatus(status);
            return repo.save(task);
        }).orElse(null);
    }


}
