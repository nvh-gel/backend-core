package com.eden.backendcore.controller;

import com.eden.backendcore.service.NewsService;
import com.eden.backendcore.viewmodel.NewsVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<Object> createNews(@RequestBody NewsVM request) {
        return ResponseEntity.ok(newsService.createOnQueue(request));
    }

    @GetMapping
    public ResponseEntity<Object> findAllNews() {
        return ResponseEntity.ok(newsService.findAll(1, 10));
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Object> findAllNewsByPaging(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(newsService.findAll(page, size));
    }

    @PutMapping
    public ResponseEntity<Object> updateNews(@RequestBody NewsVM request) {
        return ResponseEntity.ok(newsService.updateOnQueue(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNews(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.deleteOnQueue(id));
    }
}
