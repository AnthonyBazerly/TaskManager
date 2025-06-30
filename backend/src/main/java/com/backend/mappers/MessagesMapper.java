package com.backend.mappers;

import com.backend.dtos.MessagesDto;
import com.backend.models.Chats;
import com.backend.models.Employees;
import com.backend.models.Messages;
import com.backend.repos.ChatsRepo;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.MessagesRepo;

public class MessagesMapper {
    public Messages toEntity(MessagesDto dto, ChatsRepo chatsRepo, EmployeesRepo employeesRepo,
            MessagesRepo messagesRepo) {
        if (dto == null) {
            return null;
        }
        Messages entity = new Messages();
        entity.setMsg_content(dto.getMsg_content());
        entity.setMsg_status(dto.getMsg_status());
        entity.setMsg_creation_date(dto.getMsg_creation_date());
        entity.setMsg_is_edited(dto.getMsg_is_edited());
        Long id = dto.getMsg_chat_id();
        if (id == null) {
            throw new IllegalArgumentException("Msg_chat_id is null");
        }
        entity.setMsg_chat(chatsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found in Msg_chat_id")));
        id = dto.getMsg_created_by_emp_id();
        if (id == null) {
            throw new IllegalArgumentException("Msg_created_by_emp_id is null");
        }
        entity.setMsg_created_by_employee(employeesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found in Msg_created_by_emp_id")));
        id = dto.getMsg_reply_message_id();
        entity.setMsg_reply_message(id == null ? null
                : messagesRepo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Message not found in Msg_reply_message_id")));
        return entity;
    }

    public MessagesDto toDto(Messages entity) {
        if (entity == null) {
            return null;
        }
        Chats chat = entity.getMsg_chat();
        if (chat == null) {
            throw new IllegalArgumentException("No chat found in Msg_chat");
        }
        Employees employee = entity.getMsg_created_by_employee();
        if (employee == null) {
            throw new IllegalArgumentException("No employee found in Msg_created_by_emp_id");
        }
        Messages message = entity.getMsg_reply_message();
        if (message == null) {
            throw new IllegalArgumentException("No message found in Msg_reply_message_id");
        }
        MessagesDto dto = new MessagesDto();
        dto.setMsg_content(entity.getMsg_content());
        dto.setMsg_status(entity.getMsg_status());
        dto.setMsg_creation_date(entity.getMsg_creation_date());
        dto.setMsg_is_edited(entity.getMsg_is_edited());
        dto.setMsg_chat_id(chat.getChat_id());
        dto.setMsg_created_by_emp_id(employee.getEmp_id());
        dto.setMsg_reply_message_id(message != null ? message.getMsg_id() : null);

        return dto;
    }
}
