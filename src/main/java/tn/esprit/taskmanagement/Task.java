package tn.esprit.taskmanagement;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Task  implements Serializable {
    private static final long serialVersionUID = 6L;
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String taskName;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ElementCollection
    @CollectionTable(name = "task_history", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "history_entry")
    private List<String> history = new ArrayList<>();

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
    public Integer getId() {
        return id;
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
    public List<String> getHistory() {
        return history;
    }
    // Setters
    public void setId(Integer id) {
        this.id = id;
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
    public void setHistory(List<String> history) {
        this.history = history;
    }
}
