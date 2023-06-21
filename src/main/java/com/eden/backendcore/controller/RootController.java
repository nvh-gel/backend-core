package com.eden.backendcore.controller;

import lombok.AllArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for root path.
 */
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class RootController {

    private BuildProperties buildProperties;

    @GetMapping
    public ResponseEntity<Object> getVersions() {
        return ResponseEntity.ok(buildProperties.getVersion());
    }

    @GetMapping("/healthz")
    public ResponseEntity<Object> getHealthCheck() {
        return ResponseEntity.ok("UP");
    }
}
