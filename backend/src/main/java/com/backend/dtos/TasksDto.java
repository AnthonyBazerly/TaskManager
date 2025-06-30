package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class TasksDto {
    private Long task_id;
    private String task_name;
    private String task_description;
    private String task_status;
    private LocalDateTime task_creation_date;
    private LocalDateTime task_due_date;
    private Long task_estimated_time;
    private Integer task_progress;
    private String task_priority;
    private Long task_chat_id;
    private Long task_project_id;
    private Set<Long> task_assigned_to_employee_ids;
    private Long task_assigned_by_emp_id;
}