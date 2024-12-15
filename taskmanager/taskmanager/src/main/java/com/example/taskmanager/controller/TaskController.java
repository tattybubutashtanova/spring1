package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private Map<Integer, Task> tasks = new HashMap<>();
    private int currentId = 1;

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(tasks.values());
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        task.setId(currentId++);
        tasks.put(task.getId(), task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
