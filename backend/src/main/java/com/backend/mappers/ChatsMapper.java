package com.backend.mappers;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.backend.dtos.ChatsDto;
import com.backend.models.Chats;
import com.backend.models.Employees;
import com.backend.models.Messages;
import com.backend.models.Tasks;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.MessagesRepo;
import com.backend.repos.TasksRepo;

@Component
public class ChatsMapper {
    public Chats toEntity(ChatsDto dto, TasksRepo tasksRepo, 
    EmployeesRepo employeesRepo, MessagesRepo messagesRepo) {
        if (dto == null) {
            return null;
        }
        Chats entity = new Chats();
        entity.setChatId(dto.getChatId());
        entity.setChatName(dto.getChatName());
        entity.setChatStatus(dto.getChatStatus());
        entity.setChatType(dto.getChatType());
        entity.setChatCreationDate(dto.getChatCreationDate());
        Long id = dto.getChatTaskId();
        entity.setChatTask(id == null ? null
                : tasksRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Chat task not found")));
        id = dto.getChatOwnerId();
        entity.setChatOwner(id == null ? null : employeesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat created by employee not found")));
        Set<Long> ids = dto.getChatEmployeesIds();
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Chat employees not found");
        } else if (ids.size() == 1) {
            throw new IllegalArgumentException("Chat must have more than one employee");
        }
        entity.setChatEmployees(ids.stream()
                .map(empId -> employeesRepo.findById(empId)
                        .orElseThrow(() -> new IllegalArgumentException("Chat employee not found")))
                .collect(Collectors.toSet()));
        ids = dto.getChatAdminsIds();
        entity.setChatAdmins(ids == null ? null : ids.stream()
                .map(empId -> employeesRepo.findById(empId)
                        .orElseThrow(() -> new IllegalArgumentException("Chat admin not found")))
                .collect(Collectors.toSet()));
        id = dto.getChatLastMessageId();
        entity.setChatLastMessage(id == null ? null : messagesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat last message not found")));
        return entity;
    }

    public ChatsDto toDto(Chats entity) {
        if (entity == null) {
            return null;
        }
        Tasks task = entity.getChatTask();
        if (task != null && task.getTaskId() == null) {
            throw new IllegalArgumentException("Chat task not found");
        }
        Set<Employees> employees = entity.getChatEmployees();
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Chat employees not found");
        } else if (employees.size() == 1) {
            throw new IllegalArgumentException("Chat must have more than one employee");
        }
        Employees owner = entity.getChatOwner();
        if (owner != null && owner.getEmpId() == null) {
            throw new IllegalArgumentException("Chat created by employee not found");
        }
        Set<Employees> admins = entity.getChatAdmins();
        if (admins == null || admins.isEmpty()) {
            throw new IllegalArgumentException("Chat admins not found");
        }
        Messages lastMessage = entity.getChatLastMessage();
        if (lastMessage != null && lastMessage.getMsgId() == null) {
            throw new IllegalArgumentException("Chat last message not found");
        }
        ChatsDto dto = new ChatsDto();
        dto.setChatId(entity.getChatId());
        dto.setChatName(entity.getChatName());
        dto.setChatStatus(entity.getChatStatus());
        dto.setChatType(entity.getChatType());
        dto.setChatCreationDate(entity.getChatCreationDate());
        dto.setChatTaskId(task == null ? null : task.getTaskId());
        dto.setChatOwnerId(owner == null ? null : owner.getEmpId());
        dto.setChatEmployeesIds(employees.stream()
                .map(Employees::getEmpId)
                .collect(Collectors.toSet()));
        dto.setChatAdminsIds(admins == null ? null : admins.stream()
                .map(Employees::getEmpId)
                .collect(Collectors.toSet()));
        dto.setChatLastMessageId(lastMessage == null ? null : lastMessage.getMsgId());
        return dto;
    }
}
