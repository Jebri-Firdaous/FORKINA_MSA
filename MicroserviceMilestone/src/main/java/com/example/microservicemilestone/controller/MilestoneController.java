package com.example.microservicemilestone.controller;

import com.example.microservicemilestone.model.Milestone;
import com.example.microservicemilestone.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/milestones")
public class MilestoneController {
    @Autowired
    private MilestoneService milestoneService;

    @PostMapping("/add")
    public ResponseEntity<Milestone> createMilestone(@RequestBody Milestone milestone) {
        return ResponseEntity.ok(milestoneService.createMilestone(milestone));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Milestone> getMilestoneById(@PathVariable Integer id) {
        return milestoneService.getMilestoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Milestone>> getAllMilestones() {
        List<Milestone> milestones = milestoneService.getAllMilestones();
        return ResponseEntity.ok(milestones);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Milestone> updateMilestone(@PathVariable Integer id, @RequestBody Milestone milestone) {
        Milestone updatedMilestone = milestoneService.updateMilestone(id, milestone);
        return updatedMilestone != null ? ResponseEntity.ok(updatedMilestone) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Integer id) {
        milestoneService.deleteMilestone(id);
        return ResponseEntity.ok().build();
    }
}
