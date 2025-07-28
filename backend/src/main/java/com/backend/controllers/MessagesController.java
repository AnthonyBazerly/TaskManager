package com.backend.controllers;

import com.backend.dtos.MessagesDto;
import com.backend.services.MessagesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("MessagesController")
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
    public MessagesDto getMessageById(@PathVariable Long id) {
        return service.getMessageById(id);
    }

    @PostMapping
    public MessagesDto createMessage(@RequestBody MessagesDto dto) {
        return service.createMessage(dto);
    }

    @PutMapping("/{id:[0-9]+}")
    public MessagesDto updateMessage(@PathVariable Long id, @RequestBody MessagesDto dto) {
        return service.updateMessage(id, dto);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void deleteMessage(@PathVariable Long id) {
        service.deleteMessage(id);
    }
}