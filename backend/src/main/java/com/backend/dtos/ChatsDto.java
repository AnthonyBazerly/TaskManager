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
    private LocalDateTime chatCreationDate;
    private LocalDateTime chatUpdatedDate;
    private Long chatTaskId;
    private Long chatCreatedByEmpId;
    private Set<Long> chatEmployeesIds;
}