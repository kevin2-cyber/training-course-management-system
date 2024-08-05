package com.kimikevin.nomad_backend.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delegates/")
public class DelegateController {
    private final DelegateService delegateService;

    @Autowired
    public DelegateController(DelegateService delegateService) {
        this.delegateService = delegateService;
    }

    @GetMapping
    public ResponseEntity<List<Delegate>> getAllDelegates() {
        List<Delegate> delegates = delegateService.getAllDelegates();
        return ResponseEntity.ok(delegates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delegate> getDelegateById(@PathVariable Long id) {
        Delegate delegate = delegateService.getDelegateById(id);
        return ResponseEntity.ok(delegate);
    }

    @PostMapping
    public ResponseEntity<Delegate> createDelegate(@RequestBody Delegate delegate) {
        Delegate createdDelegate = delegateService.createDelegate(delegate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDelegate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delegate> updateDelegate(@PathVariable Long id, @RequestBody Delegate delegateDetails) {
        Delegate updatedDelegate = delegateService.updateDelegate(id, delegateDetails);
        return ResponseEntity.ok(updatedDelegate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelegate(@PathVariable Long id) {
        delegateService.deleteDelegate(id);
        return ResponseEntity.noContent().build();
    }
}
