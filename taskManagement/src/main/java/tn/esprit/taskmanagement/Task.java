package tn.esprit.taskmanagement;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
public class Task  implements Serializable {
    private static final long serialVersionUID = 6L;
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTask;
    private String taskName;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

private LocalDateTime dueDate;
public  Task (String nom, String desc, Status status,LocalDateTime ddl)
{
this.dueDate=ddl;
this.description=desc;
this.taskName=nom;
this.status= status;
}

    public  Task ()
    {

    }
    // Getters
    public Integer getIdTask() {
        return idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    // Setters
    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

}
