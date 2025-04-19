package com.example.serviceprojecthamzambarki.repository;

import com.example.serviceprojecthamzambarki.entity.Project;
import com.example.serviceprojecthamzambarki.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)  // Disable Spring Data REST exposure
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByUserId(Integer userId);
    List<Project> findByProjectStatus(Status projectStatus);}