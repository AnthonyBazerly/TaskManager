package com.backend.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import com.backend.dtos.ChatsDto;
import com.backend.models.Chats;
import com.backend.models.Employees;
import com.backend.models.Tasks;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.TasksRepo;

public class ChatsMapper {
    public Chats toEntity(ChatsDto dto, TasksRepo tasksRepo, EmployeesRepo employeesRepo) {
        if (dto == null) {
            return null;
        }
        Chats entity = new Chats();
        entity.setChat_id(dto.getChat_id());
        entity.setChat_name(dto.getChat_name());
        entity.setChat_status(dto.getChat_status());
        entity.setChat_creation_date(dto.getChat_creation_date());
        entity.setChat_updated_date(dto.getChat_updated_date());
        Long id = dto.getChat_task_id();
        entity.setChat_task(id == null ? null
                : tasksRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Chat task not found")));
        id = dto.getChat_created_by_emp_id();
        if (id == null) {
            throw new IllegalArgumentException("Chat created by employee ID is null");
        }
        entity.setChat_created_by_employee(employeesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat created by employee not found")));
        Set<Long> ids = dto.getChat_employees_ids();
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Chat employees IDs is null");
        }
        entity.setChat_employees(ids.stream()
                .map(empId -> employeesRepo.findById(empId)
                        .orElseThrow(() -> new IllegalArgumentException("Chat employee not found")))
                .collect(Collectors.toSet()));
        return entity;
    }

    public ChatsDto toDto(Chats entity) {
        if (entity == null) {
            return null;
        }
        Tasks task = entity.getChat_task();
        if (task == null) {
            throw new IllegalArgumentException("Chat task not found");
        }
        Set<Employees> employees = entity.getChat_employees();
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Chat employees not found");
        }
        Employees employee = entity.getChat_created_by_employee();
        if (employee == null) {
            throw new IllegalArgumentException("Chat created by employee not found");
        }
        ChatsDto dto = new ChatsDto();
        dto.setChat_id(entity.getChat_id());
        dto.setChat_name(entity.getChat_name());
        dto.setChat_status(entity.getChat_status());
        dto.setChat_creation_date(entity.getChat_creation_date());
        dto.setChat_updated_date(entity.getChat_updated_date());
        dto.setChat_task_id(task.getTask_id());
        dto.setChat_created_by_emp_id(employee.getEmp_id());
        dto.setChat_employees_ids(employees.stream()
                .map(Employees::getEmp_id)
                .collect(Collectors.toSet()));
        return dto;
    }
}
