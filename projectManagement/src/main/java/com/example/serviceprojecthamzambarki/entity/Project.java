package com.example.serviceprojecthamzambarki.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProject;
    @NotBlank(message = "Content cannot be empty")
    private String title;
    @NotBlank(message = "Content cannot be empty")
    private String content;
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private Status projectStatus;

    @ElementCollection
    private List<String> label;

    private Integer teamRef;
    @JsonProperty("userId")
    private Integer userId;  // New field for assigned user
}