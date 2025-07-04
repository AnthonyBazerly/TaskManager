package com.backend.mappers;

import com.backend.dtos.TasksDto;
import com.backend.models.Tasks;
import com.backend.models.Chats;
import com.backend.models.Employees;
import com.backend.repos.ChatsRepo;
import com.backend.repos.EmployeesRepo;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TasksMapper {
    public Tasks toEntity(TasksDto dto, EmployeesRepo employeesRepo, ChatsRepo chatsRepo) {
        if (dto == null) {
            return null;
        }
        Tasks entity = new Tasks();
        entity.setTaskId(dto.getTaskId());
        entity.setTaskName(dto.getTaskName());
        entity.setTaskDescription(dto.getTaskDescription());
        entity.setTaskStatus(dto.getTaskStatus());
        entity.setTaskCreationDate(dto.getTaskCreationDate());
        entity.setTaskDueDate(dto.getTaskDueDate());
        entity.setTaskEstimatedTime(dto.getTaskEstimatedTime());
        entity.setTaskProgress(dto.getTaskProgress());
        entity.setTaskPriority(dto.getTaskPriority());
        Long id = dto.getTaskChatId();
        if (id == null) {
            throw new IllegalArgumentException("TaskChatId is null");
        }
        entity.setTaskChat(chatsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found in TaskChatId")));
        entity.setTaskProjectId(dto.getTaskProjectId());
        Set<Long> ids = dto.getTaskAssignedToEmployeeIds();
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("No employee found in TaskAssignedToEmployeeIds");
        }
        entity.setTaskAssignedToEmployees(ids.stream()
                .map(id2 -> employeesRepo.findById(id2)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Employee not found while mapping TaskAssignedToEmployeeIds")))
                .collect(Collectors.toSet()));
        id = dto.getTaskAssignedByEmpId();
        if (id == null) {
            throw new IllegalArgumentException("TaskAssignedByEmpId is null");
        }
        entity.setTaskAssignedByEmployee(employeesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found in TaskAssignedByEmployeeId")));

        return entity;
    }

    public TasksDto toDto(Tasks entity) {
        if (entity == null) {
            return null;
        }
        Set<Employees> employees = entity.getTaskAssignedToEmployees();
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("No employees found in TaskAssignedToEmployeeIds");
        }
        Employees employee = entity.getTaskAssignedByEmployee();
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found in TaskAssignedByEmployeeId");
        }
        Chats chat = entity.getTaskChat();
        if (chat == null) {
            throw new IllegalArgumentException("Chat not found in TaskChatId");
        }
        TasksDto dto = new TasksDto();
        dto.setTaskId(entity.getTaskId());
        dto.setTaskName(entity.getTaskName());
        dto.setTaskDescription(entity.getTaskDescription());
        dto.setTaskStatus(entity.getTaskStatus());
        dto.setTaskCreationDate(entity.getTaskCreationDate());
        dto.setTaskDueDate(entity.getTaskDueDate());
        dto.setTaskEstimatedTime(entity.getTaskEstimatedTime());
        dto.setTaskProgress(entity.getTaskProgress());
        dto.setTaskPriority(entity.getTaskPriority());
        dto.setTaskChatId(chat.getChatId());
        dto.setTaskProjectId(entity.getTaskProjectId());
        dto.setTaskAssignedToEmployeeIds(
                employees.stream()
                        .map(Employees::getEmpId)
                        .collect(Collectors.toSet()));
        dto.setTaskAssignedByEmpId(employee.getEmpId());
        return dto;
    }
}