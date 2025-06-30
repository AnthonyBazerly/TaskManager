package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class ChatsDto {
    private Long chat_id;
    private String chat_name;
    private String chat_status;
    private LocalDateTime chat_creation_date;
    private LocalDateTime chat_updated_date;
    private Long chat_task_id;
    private Long chat_created_by_emp_id;
    private Set<Long> chat_employees_ids;
}