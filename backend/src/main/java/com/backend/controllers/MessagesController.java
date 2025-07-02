package com.backend.controllers;

import com.backend.dtos.MessagesDto;
import com.backend.services.MessagesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    private final MessagesService service;

    public MessagesController(MessagesService service) {
        this.service = service;
    }

    @GetMapping
    public List<MessagesDto> getAllMessages() {
        return service.getAllMessages();
    }

    @GetMapping("/{id:[0-9]+}")
    public MessagesDto getJobTypeById(@PathVariable Long id) {
        return service.getJobTypeById(id);
    }

    @PostMapping
    public MessagesDto createJobType(@RequestBody MessagesDto dto) {
        return service.createJobType(dto);
    }

    @PutMapping("/{id:[0-9]+}")
    public MessagesDto updateJobType(@PathVariable Long id, @RequestBody MessagesDto dto) {
        return service.updateJobType(id, dto);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void deleteJobType(@PathVariable Long id) {
        service.deleteJobType(id);
    }
}