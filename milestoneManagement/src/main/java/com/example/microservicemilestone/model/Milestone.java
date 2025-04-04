// Milestone.java
package com.example.microservicemilestone.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "milestones")
public class Milestone implements Serializable {
    private static final long serialVersionUID = 6L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically increments the id
    private Integer idMilstone;
    private String milestoneName;
    private String description;

    private LocalDateTime dueDate;


    private Integer projectRef;

    private Integer taskRef;

    // Default constructor
    public Milestone() {}

    public Milestone(String milestoneName, String description, LocalDateTime dueDate, Integer projectRef, Integer taskRef) {
        this.milestoneName = milestoneName;
        this.description = description;
        this.dueDate = dueDate;
        this.projectRef = projectRef;
        this.taskRef = taskRef;
    }

    // Getters and Setters
    public Integer getId() { return idMilstone; }
    public void setId(Integer id) { this.idMilstone = id; }

    public String getMilestoneName() { return milestoneName; }
    public void setMilestoneName(String milestoneName) { this.milestoneName = milestoneName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public Integer getProjectRef() { return projectRef; }
    public void setProjectRef(Integer projectRef) { this.projectRef = projectRef; }

    public Integer getTaskRef() { return taskRef; }
    public void setTaskRef(Integer taskRef) { this.taskRef = taskRef; }
}
