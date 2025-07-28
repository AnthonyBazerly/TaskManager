package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class ChatsDto {
    private Long chatId;
    private String chatName;
    private String chatStatus;
    private String chatType;
    private LocalDateTime chatCreationDate;
    private LocalDateTime chatLastUpdate;
    private Long chatTaskId;
    private String chatTask;
    private Long chatOwnerId;
    private String chatOwner;
    private Set<Long> chatEmployeesIds;
    private Set<String> chatEmployees;
    private Set<Long> chatAdminsIds;
    private Set<String> chatAdmins;
    private Long chatLastMessageId;
    private String chatLastMessage;
}