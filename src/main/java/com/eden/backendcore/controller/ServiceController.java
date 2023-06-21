package com.eden.backendcore.controller;

import com.eden.backendcore.service.ServiceDataService;
import com.eden.backendcore.viewmodel.ServiceVM;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
@AllArgsConstructor
public class ServiceController {

    private ServiceDataService serviceDataService;

    @PostMapping
    public ResponseEntity<Object> createService(@RequestBody ServiceVM request) {
        return ResponseEntity.accepted().body(serviceDataService.createOnQueue(request));
    }

    @GetMapping
    public ResponseEntity<Object> findAllService() {
        return ResponseEntity.ok().body(serviceDataService.findAll(1, 10));
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Object> findAllServiceByPaging(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok().body(serviceDataService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findServiceById(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceDataService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Object> updateService(@RequestBody ServiceVM request) {
        return ResponseEntity.ok().body(serviceDataService.updateOnQueue(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable Long id) {
        return ResponseEntity.ok(serviceDataService.deleteOnQueue(id));
    }
}
