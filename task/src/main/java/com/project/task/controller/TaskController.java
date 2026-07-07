package com.project.task.controller;

import com.project.task.dto.TaskRequest;
import com.project.task.dto.TaskResponse;
import com.project.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService service;

    @GetMapping("/")
    public ResponseEntity<List<TaskResponse>> getAll()
    {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<TaskResponse> add(@RequestBody TaskRequest request)
    {
        return ResponseEntity.ok().body(service.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateById(@PathVariable Long id,@RequestBody TaskRequest request)
    {
        return ResponseEntity.ok().body(service.updateById(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body("Task with "+id+" deleted sucessfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> patchById(@PathVariable Long id, @RequestParam String status)
    {
        return ResponseEntity.ok().body(service.PatchById(id,status));
    }

}
