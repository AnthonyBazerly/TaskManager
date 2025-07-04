package com.backend.mappers;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.backend.dtos.ChatsDto;
import com.backend.models.Chats;
import com.backend.models.Employees;
import com.backend.models.Tasks;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.TasksRepo;

@Component
public class ChatsMapper {
    public Chats toEntity(ChatsDto dto, TasksRepo tasksRepo, EmployeesRepo employeesRepo) {
        if (dto == null) {
            return null;
        }
        Chats entity = new Chats();
        entity.setChatId(dto.getChatId());
        entity.setChatName(dto.getChatName());
        entity.setChatStatus(dto.getChatStatus());
        entity.setChatCreationDate(dto.getChatCreationDate());
        entity.setChatUpdatedDate(dto.getChatUpdatedDate());
        Long id = dto.getChatTaskId();
        entity.setChatTask(id == null ? null
                : tasksRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Chat task not found")));
        id = dto.getChatCreatedByEmpId();
        if (id == null) {
            throw new IllegalArgumentException("Chat created by employee ID is null");
        }
        entity.setChatCreatedByEmployee(employeesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat created by employee not found")));
        Set<Long> ids = dto.getChatEmployeesIds();
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Chat employees IDs is null");
        }
        entity.setChatEmployees(ids.stream()
                .map(empId -> employeesRepo.findById(empId)
                        .orElseThrow(() -> new IllegalArgumentException("Chat employee not found")))
                .collect(Collectors.toSet()));
        return entity;
    }

    public ChatsDto toDto(Chats entity) {
        if (entity == null) {
            return null;
        }
        Tasks task = entity.getChatTask();
        if (task == null) {
            throw new IllegalArgumentException("Chat task not found");
        }
        Set<Employees> employees = entity.getChatEmployees();
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Chat employees not found");
        }
        Employees employee = entity.getChatCreatedByEmployee();
        if (employee == null) {
            throw new IllegalArgumentException("Chat created by employee not found");
        }
        ChatsDto dto = new ChatsDto();
        dto.setChatId(entity.getChatId());
        dto.setChatName(entity.getChatName());
        dto.setChatStatus(entity.getChatStatus());
        dto.setChatCreationDate(entity.getChatCreationDate());
        dto.setChatUpdatedDate(entity.getChatUpdatedDate());
        dto.setChatTaskId(task.getTaskId());
        dto.setChatCreatedByEmpId(employee.getEmpId());
        dto.setChatEmployeesIds(employees.stream()
                .map(Employees::getEmpId)
                .collect(Collectors.toSet()));
        return dto;
    }
}
