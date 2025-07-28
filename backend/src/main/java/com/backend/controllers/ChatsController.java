package com.backend.controllers;

import com.backend.dtos.ChatsDto;
import com.backend.services.ChatsService;
import com.backend.services.EmployeesService;
import com.backend.services.MessagesService;
import com.backend.services.TasksService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController("ChatsController")
@RequestMapping("/api/chats")
public class ChatsController {
    private final ChatsService service;
    private final TasksService tasksService;
    private final EmployeesService employeesService;
    private final MessagesService messagesService;

    public ChatsController(ChatsService service, TasksService tasksService,
    EmployeesService employeesService, MessagesService messagesService) {
        this.service = service;
        this.tasksService = tasksService;
        this.employeesService = employeesService;
        this.messagesService = messagesService;
    }

    @GetMapping
    public List<ChatsDto> getAllChats() {
        List<ChatsDto> chats = service.getAllChats();

        for (ChatsDto chat : chats) {
            Long taskId = chat.getChatTaskId(), ownerId = chat.getChatOwnerId(),
            messageId = chat.getChatLastMessageId();
            Set<Long> employeeIds = chat.getChatEmployeesIds(), adminsIds = chat.getChatAdminsIds();

            if (taskId != null) {
                chat.setChatTask(tasksService.getTaskById(taskId).getTaskName());
            } else {
                chat.setChatTask("N/A");
            }

            if (ownerId != null) {
                chat.setChatOwner(employeesService.getEmployeeById(ownerId).getEmpFirstName() + " " + 
                                         employeesService.getEmployeeById(ownerId).getEmpLastName());
            } else {
                chat.setChatOwner("N/A");
            }
            
            if (employeeIds != null) {
                Set<String> employeeNames = new java.util.HashSet<String>();
                for (Long empId : employeeIds) {
                    employeeNames.add(employeesService.getEmployeeById(empId).getEmpFirstName() + " " + 
                                      employeesService.getEmployeeById(empId).getEmpLastName());
                }
                chat.setChatEmployees(employeeNames);
            }

            if (adminsIds != null) {
                Set<String> adminNames = new java.util.HashSet<String>();
                for (Long adminId : adminsIds) {
                    adminNames.add(employeesService.getEmployeeById(adminId).getEmpFirstName() + " " + 
                                   employeesService.getEmployeeById(adminId).getEmpLastName());
                }
                chat.setChatAdmins(adminNames);
            } else {
                chat.setChatAdmins(Set.of("N/A"));
            }

            if (messageId != null) {
                chat.setChatLastMessage(messagesService.getMessageById(messageId).getMsgContent());
                chat.setChatLastUpdate(messagesService.getMessageById(messageId).getMsgCreationDate());
            } else {
                chat.setChatLastMessage("N/A");
                chat.setChatLastUpdate(null);
            }
        }
        return chats;
    }

    @GetMapping("/{id:[0-9]+}")
    public ChatsDto getChatTaskById(@PathVariable Long id) {
        ChatsDto chat = service.getChatById(id);

        if (chat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
        }

        Long taskId = chat.getChatTaskId(), createdById = chat.getChatOwnerId(),
        messageId = chat.getChatLastMessageId();
        Set<Long> employeeIds = chat.getChatEmployeesIds(), adminsIds = chat.getChatAdminsIds();

        if (taskId != null) {
            chat.setChatTask(tasksService.getTaskById(taskId).getTaskName());
        } else {
            chat.setChatTask("N/A");
        }

        if (createdById != null) {
            chat.setChatOwner(employeesService.getEmployeeById(createdById).getEmpFirstName() + " " + 
                                     employeesService.getEmployeeById(createdById).getEmpLastName());
        } else {
            chat.setChatOwner("N/A");
        }

        if (employeeIds != null) {
            Set<String> employeeNames = new java.util.HashSet<String>();
            for (Long empId : employeeIds) {
                employeeNames.add(employeesService.getEmployeeById(empId).getEmpFirstName() + " " + 
                                  employeesService.getEmployeeById(empId).getEmpLastName());
            }
            chat.setChatEmployees(employeeNames);
        }

        if (adminsIds != null) {
            Set<String> adminNames = new java.util.HashSet<String>();
            for (Long adminId : adminsIds) {
                adminNames.add(employeesService.getEmployeeById(adminId).getEmpFirstName() + " " + 
                               employeesService.getEmployeeById(adminId).getEmpLastName());
            }
            chat.setChatAdmins(adminNames);
        } else {
            chat.setChatAdmins(Set.of("N/A"));
        }

        if (messageId != null) {
            chat.setChatLastMessage(messagesService.getMessageById(messageId).getMsgContent());
            chat.setChatLastUpdate(messagesService.getMessageById(messageId).getMsgCreationDate());
        } else {
            chat.setChatLastMessage("N/A");
            chat.setChatLastUpdate(null);
        }

        return chat;
    }

    @PostMapping
    public ChatsDto createChat(@RequestBody ChatsDto dto) {
        return service.createChat(dto);
    }

    @PutMapping("/{id:[0-9]+}")
    public ChatsDto updateChat(@PathVariable Long id, @RequestBody ChatsDto dto) {
        return service.updateChat(id, dto);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void deleteChat(@PathVariable Long id) {
        service.deleteChat(id);
    }
}