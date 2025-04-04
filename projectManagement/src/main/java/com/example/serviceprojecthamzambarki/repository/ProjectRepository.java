package com.example.serviceprojecthamzambarki.repository;

import com.example.serviceprojecthamzambarki.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées si besoin
}