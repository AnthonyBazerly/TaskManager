package com.backend.services;

import com.backend.dtos.ChatsDto;
import com.backend.models.Chats;
import com.backend.repos.ChatsRepo;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.TasksRepo;
import com.backend.mappers.ChatsMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatsService {
    private final ChatsRepo ChatsRepo;
    private final EmployeesRepo employeesRepo;
    private final TasksRepo tasksRepo;
    private final ChatsMapper ChatsMapper;

    public ChatsService(ChatsRepo ChatsRepo, EmployeesRepo employeesRepo, TasksRepo tasksRepo,
            ChatsMapper ChatsMapper) {
        this.ChatsRepo = ChatsRepo;
        this.employeesRepo = employeesRepo;
        this.tasksRepo = tasksRepo;
        this.ChatsMapper = ChatsMapper;
    }

    public List<ChatsDto> getAllChats() {
        return ChatsRepo.findAll().stream()
                .map(ChatsMapper::toDto)
                .collect(Collectors.toList());
    }

    public ChatsDto getTaskById(Long id) {
        return ChatsRepo.findById(id)
                .map(ChatsMapper::toDto)
                .orElse(null);
    }

    public ChatsDto createTask(ChatsDto dto) {
        Chats task = ChatsMapper.toEntity(dto, tasksRepo, employeesRepo);
        Chats saved = ChatsRepo.save(task);
        return ChatsMapper.toDto(saved);
    }

    public ChatsDto updateTask(Long id, ChatsDto dto) {
        return ChatsRepo.findById(id)
                .map(existing -> {
                    existing = ChatsMapper.toEntity(dto, tasksRepo, employeesRepo);
                    Chats updated = ChatsRepo.save(existing);
                    return ChatsMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteTask(Long id) {
        ChatsRepo.deleteById(id);
    }
}