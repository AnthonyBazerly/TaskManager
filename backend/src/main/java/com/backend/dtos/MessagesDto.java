package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagesDto {
    private String msgContent;
    private String msgStatus;
    private LocalDateTime msgCreationDate;
    private Boolean msgIsEdited;
    private Long msgChatId;
    private Long msgCreatedByEmpId;
    private Long msgReplyMessageId;
}
