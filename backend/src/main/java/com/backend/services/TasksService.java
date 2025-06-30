package com.backend.services;

import com.backend.dtos.TasksDto;
import com.backend.models.Tasks;
import com.backend.repos.TasksRepo;
import com.backend.repos.ChatsRepo;
import com.backend.repos.EmployeesRepo;
import com.backend.mappers.TasksMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksService {
    private final TasksRepo tasksRepo;
    private final EmployeesRepo employeesRepo;
    private final ChatsRepo chatsRepo;
    private final TasksMapper tasksMapper;

    public TasksService(TasksRepo tasksRepo, EmployeesRepo employeesRepo, ChatsRepo chatsRepo,
            TasksMapper tasksMapper) {
        this.tasksRepo = tasksRepo;
        this.employeesRepo = employeesRepo;
        this.chatsRepo = chatsRepo;
        this.tasksMapper = tasksMapper;
    }

    public List<TasksDto> getAllTasks() {
        return tasksRepo.findAll().stream()
                .map(tasksMapper::toDto)
                .collect(Collectors.toList());
    }

    public TasksDto getTaskById(Long id) {
        return tasksRepo.findById(id)
                .map(tasksMapper::toDto)
                .orElse(null);
    }

    public TasksDto createTask(TasksDto dto) {
        Tasks task = tasksMapper.toEntity(dto, employeesRepo, chatsRepo);
        Tasks saved = tasksRepo.save(task);
        return tasksMapper.toDto(saved);
    }

    public TasksDto updateTask(Long id, TasksDto dto) {
        return tasksRepo.findById(id)
                .map(existing -> {
                    existing = tasksMapper.toEntity(dto, employeesRepo, chatsRepo);
                    Tasks updated = tasksRepo.save(existing);
                    return tasksMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteTask(Long id) {
        tasksRepo.deleteById(id);
    }
}