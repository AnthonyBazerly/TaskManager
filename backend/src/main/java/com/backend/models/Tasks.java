package com.backend.models;

import java.time.LocalDateTime;
import java.util.Set;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskName;
    private String taskDescription;
    private String taskStatus; // Not Started, In Progress, Blocked, On Hold, Completed, Canceled, Pending
                               // Review, Under Review, Approved, Rejected
    private LocalDateTime taskCreationDate;
    @Nullable
    private LocalDateTime taskDueDate;
    private Long taskEstimatedTime;
    private Integer taskProgress; // 0-100%
    private String taskPriority; // Urgent, Critical, High, Regular, Low, Routine, Optional
    private Long taskProjectId; // project table needed
    // attachments can be added

    @ManyToMany
    @JoinTable(name = "taskAssignedToEmployees", joinColumns = @JoinColumn(name = "taskId"), inverseJoinColumns = @JoinColumn(name = "empId"))
    private Set<Employees> taskAssignedToEmployees;

    @ManyToOne
    @JoinColumn(name = "taskAssignedByEmpId", referencedColumnName = "empId")
    private Employees taskAssignedByEmployee;

    @OneToOne
    @JoinColumn(name = "taskChatId", referencedColumnName = "chatId")
    @Nullable
    private Chats taskChat;
}
