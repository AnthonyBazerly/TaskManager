package com.backend.services;

import com.backend.dtos.ChatsDto;
import com.backend.models.Chats;
import com.backend.repos.ChatsRepo;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.MessagesRepo;
import com.backend.repos.TasksRepo;
import com.backend.mappers.ChatsMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatsService {
    private final ChatsRepo ChatsRepo;
    private final EmployeesRepo employeesRepo;
    private final TasksRepo TasksRepo;
    private final MessagesRepo messagesRepo;
    private final ChatsMapper ChatsMapper;

    public ChatsService(ChatsRepo ChatsRepo, EmployeesRepo employeesRepo, TasksRepo TasksRepo,
            MessagesRepo messagesRepo, ChatsMapper ChatsMapper) {
        this.ChatsRepo = ChatsRepo;
        this.employeesRepo = employeesRepo;
        this.TasksRepo = TasksRepo;
        this.messagesRepo = messagesRepo;
        this.ChatsMapper = ChatsMapper;
    }

    public List<ChatsDto> getAllChats() {
        return ChatsRepo.findAll().stream()
                .map(ChatsMapper::toDto)
                .collect(Collectors.toList());
    }

    public ChatsDto getChatById(Long id) {
        return ChatsRepo.findById(id)
                .map(ChatsMapper::toDto)
                .orElse(null);
    }

    public ChatsDto createChat(ChatsDto dto) {
        Chats chat = ChatsMapper.toEntity(dto, TasksRepo, employeesRepo, messagesRepo);
        Chats saved = ChatsRepo.save(chat);
        return ChatsMapper.toDto(saved);
    }

    public ChatsDto updateChat(Long id, ChatsDto dto) {
        return ChatsRepo.findById(id)
                .map(existing -> {
                    existing = ChatsMapper.toEntity(dto, TasksRepo, employeesRepo, messagesRepo);
                    Chats updated = ChatsRepo.save(existing);
                    return ChatsMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteChat(Long id) {
        ChatsRepo.deleteById(id);
    }
}