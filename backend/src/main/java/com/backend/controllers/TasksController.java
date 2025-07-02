package com.backend.controllers;

import com.backend.dtos.TasksDto;
import com.backend.services.TasksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {
    private final TasksService service;

    public TasksController(TasksService service) {
        this.service = service;
    }

    @GetMapping
    public List<TasksDto> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id:[0-9]+}")
    public TasksDto getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @PostMapping
    public TasksDto createTask(@RequestBody TasksDto dto) {
        return service.createTask(dto);
    }

    @PutMapping("/{id:[0-9]+}")
    public TasksDto updateTask(@PathVariable Long id, @RequestBody TasksDto dto) {
        return service.updateTask(id, dto);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}