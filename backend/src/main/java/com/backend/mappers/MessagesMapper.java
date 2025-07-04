package com.backend.mappers;

import org.springframework.stereotype.Component;
import com.backend.dtos.MessagesDto;
import com.backend.models.Chats;
import com.backend.models.Employees;
import com.backend.models.Messages;
import com.backend.repos.ChatsRepo;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.MessagesRepo;

@Component
public class MessagesMapper {
    public Messages toEntity(MessagesDto dto, ChatsRepo chatsRepo, EmployeesRepo employeesRepo,
            MessagesRepo messagesRepo) {
        if (dto == null) {
            return null;
        }
        Messages entity = new Messages();
        entity.setMsgContent(dto.getMsgContent());
        entity.setMsgStatus(dto.getMsgStatus());
        entity.setMsgCreationDate(dto.getMsgCreationDate());
        entity.setMsgIsEdited(dto.getMsgIsEdited());
        Long id = dto.getMsgChatId();
        if (id == null) {
            throw new IllegalArgumentException("MsgChatId is null");
        }
        entity.setMsgChat(chatsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found in MsgChatId")));
        id = dto.getMsgCreatedByEmpId();
        if (id == null) {
            throw new IllegalArgumentException("MsgCreatedByEmpId is null");
        }
        entity.setMsgCreatedByEmployee(employeesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found in MsgCreatedByEmpId")));
        id = dto.getMsgReplyMessageId();
        entity.setMsgReplyMessage(id == null ? null
                : messagesRepo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Message not found in MsgReplyMessageId")));
        return entity;
    }

    public MessagesDto toDto(Messages entity) {
        if (entity == null) {
            return null;
        }
        Chats chat = entity.getMsgChat();
        if (chat == null) {
            throw new IllegalArgumentException("No chat found in MsgChat");
        }
        Employees employee = entity.getMsgCreatedByEmployee();
        if (employee == null) {
            throw new IllegalArgumentException("No employee found in MsgCreatedByEmpId");
        }
        Messages message = entity.getMsgReplyMessage();
        if (message == null) {
            throw new IllegalArgumentException("No message found in MsgReplyMessageId");
        }
        MessagesDto dto = new MessagesDto();
        dto.setMsgContent(entity.getMsgContent());
        dto.setMsgStatus(entity.getMsgStatus());
        dto.setMsgCreationDate(entity.getMsgCreationDate());
        dto.setMsgIsEdited(entity.getMsgIsEdited());
        dto.setMsgChatId(chat.getChatId());
        dto.setMsgCreatedByEmpId(employee.getEmpId());
        dto.setMsgReplyMessageId(message != null ? message.getMsgId() : null);

        return dto;
    }
}
