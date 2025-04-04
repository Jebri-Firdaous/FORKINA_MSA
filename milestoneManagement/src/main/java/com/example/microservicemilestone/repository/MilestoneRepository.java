// MilestoneRepository.java
package com.example.microservicemilestone.repository;

import com.example.microservicemilestone.model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Integer> {}
