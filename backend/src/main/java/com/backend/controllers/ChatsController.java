package com.backend.controllers;

import com.backend.dtos.ChatsDto;
import com.backend.services.ChatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatsController {
    private final ChatsService service;

    public ChatsController(ChatsService service) {
        this.service = service;
    }

    @GetMapping
    public List<ChatsDto> getAllChats() {
        return service.getAllChats();
    }

    @GetMapping("/{id:[0-9]+}")
    public ChatsDto getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @PostMapping
    public ChatsDto createTask(@RequestBody ChatsDto dto) {
        return service.createTask(dto);
    }

    @PutMapping("/{id:[0-9]+}")
    public ChatsDto updateTask(@PathVariable Long id, @RequestBody ChatsDto dto) {
        return service.updateTask(id, dto);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}