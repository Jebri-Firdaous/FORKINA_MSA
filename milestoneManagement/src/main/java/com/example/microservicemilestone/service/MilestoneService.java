package com.example.microservicemilestone.service;

import com.example.microservicemilestone.model.Milestone;
import com.example.microservicemilestone.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MilestoneService {
    @Autowired
    private MilestoneRepository milestoneRepository;

    public Milestone createMilestone(Milestone milestone) {
        return milestoneRepository.save(milestone);
    }

    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }
    public Optional<Milestone> getMilestoneById(Integer id) {
        return milestoneRepository.findById(id);
    }

    public Milestone updateMilestone(Integer id, Milestone milestone) {
        if (milestoneRepository.existsById(id)) {
            milestone.setId(id);
            return milestoneRepository.save(milestone);
        }
        return null;
    }

    public void deleteMilestone(Integer id) {
        milestoneRepository.deleteById(id);
    }
}
