package com.eden.backendcore.controller;

import com.eden.backendcore.service.ProjectService;
import com.eden.backendcore.viewmodel.ProjectVM;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("project")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Object> createProject(@RequestBody ProjectVM request) {
        return ResponseEntity.ok(projectService.createOnQueue(request));
    }

    @GetMapping
    public ResponseEntity<Object> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll(1, 10));
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Object> getProjectsByPaging(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(projectService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Object> updateProject(@RequestBody ProjectVM request) {
        return ResponseEntity.ok(projectService.updateOnQueue(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteOnQueue(id));
    }
}
