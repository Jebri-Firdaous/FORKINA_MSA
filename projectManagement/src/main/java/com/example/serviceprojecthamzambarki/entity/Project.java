package com.example.serviceprojecthamzambarki.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    private String title;
    private String content;

    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private Status projectStatus;

    @ElementCollection
    private List<String> label;

    private Integer teamRef;




}
