package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class TasksDto {
    private Long taskId;
    private String taskName;
    private String taskDescription;
    private String taskStatus;
    private LocalDateTime taskCreationDate;
    private LocalDateTime taskDueDate;
    private Long taskEstimatedTime;
    private Integer taskProgress;
    private String taskPriority;
    private Long taskChatId;
    private Long taskProjectId;
    private Set<Long> taskAssignedToEmployeeIds;
    private Long taskAssignedByEmpId;
}