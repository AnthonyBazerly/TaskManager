package com.backend.mappers;

import com.backend.dtos.TasksDto;
import com.backend.models.Tasks;
import com.backend.models.Chats;
import com.backend.models.Employees;
import com.backend.repos.ChatsRepo;
import com.backend.repos.EmployeesRepo;
import java.util.Set;
import java.util.stream.Collectors;

public class TasksMapper {
    public Tasks toEntity(TasksDto dto, EmployeesRepo employeesRepo, ChatsRepo chatsRepo) {
        if (dto == null) {
            return null;
        }
        Tasks entity = new Tasks();
        entity.setTask_id(dto.getTask_id());
        entity.setTask_name(dto.getTask_name());
        entity.setTask_description(dto.getTask_description());
        entity.setTask_status(dto.getTask_status());
        entity.setTask_creation_date(dto.getTask_creation_date());
        entity.setTask_due_date(dto.getTask_due_date());
        entity.setTask_estimated_time(dto.getTask_estimated_time());
        entity.setTask_progress(dto.getTask_progress());
        entity.setTask_priority(dto.getTask_priority());
        Long id = dto.getTask_chat_id();
        if (id == null) {
            throw new IllegalArgumentException("Task_chat_id is null");
        }
        entity.setTask_chat(chatsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found in Task_chat_id")));
        entity.setTask_project_id(dto.getTask_project_id());
        Set<Long> ids = dto.getTask_assigned_to_employee_ids();
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("No employee found in Task_assigned_to_employee_ids");
        }
        entity.setTask_assigned_to_employees(ids.stream()
                .map(id2 -> employeesRepo.findById(id2)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Employee not found while mapping Task_assigned_to_employee_ids")))
                .collect(Collectors.toSet()));
        id = dto.getTask_assigned_by_emp_id();
        if (id == null) {
            throw new IllegalArgumentException("Task_assigned_by_emp_id is null");
        }
        entity.setTask_assigned_by_employee(employeesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found in Task_assigned_by_employee_id")));

        return entity;
    }

    public TasksDto toDto(Tasks entity) {
        if (entity == null) {
            return null;
        }
        Set<Employees> employees = entity.getTask_assigned_to_employees();
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("No employees found in Task_assigned_to_employee_ids");
        }
        Employees employee = entity.getTask_assigned_by_employee();
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found in Task_assigned_by_employee_id");
        }
        Chats chat = entity.getTask_chat();
        if (chat == null) {
            throw new IllegalArgumentException("Chat not found in Task_chat_id");
        }
        TasksDto dto = new TasksDto();
        dto.setTask_id(entity.getTask_id());
        dto.setTask_name(entity.getTask_name());
        dto.setTask_description(entity.getTask_description());
        dto.setTask_status(entity.getTask_status());
        dto.setTask_creation_date(entity.getTask_creation_date());
        dto.setTask_due_date(entity.getTask_due_date());
        dto.setTask_estimated_time(entity.getTask_estimated_time());
        dto.setTask_progress(entity.getTask_progress());
        dto.setTask_priority(entity.getTask_priority());
        dto.setTask_chat_id(chat.getChat_id());
        dto.setTask_project_id(entity.getTask_project_id());
        dto.setTask_assigned_to_employee_ids(
                employees.stream()
                        .map(Employees::getEmp_id)
                        .collect(Collectors.toSet()));
        dto.setTask_assigned_by_emp_id(employee.getEmp_id());
        return dto;
    }
}