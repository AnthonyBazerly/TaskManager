package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagesDto {
    private String msg_content;
    private String msg_status;
    private LocalDateTime msg_creation_date;
    private Boolean msg_is_edited;
    private Long msg_chat_id;
    private Long msg_created_by_emp_id;
    private Long msg_reply_message_id;
}
