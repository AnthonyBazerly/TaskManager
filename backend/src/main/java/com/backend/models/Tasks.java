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
    private Long task_id;
    private String task_name;
    private String task_description;
    private String task_status; // Not Started, In Progress, Blocked, On Hold, Completed, Canceled, Pending
                                // Review, Under Review, Approved, Rejected
    private LocalDateTime task_creation_date;
    @Nullable
    private LocalDateTime task_due_date;
    private Long task_estimated_time;
    private Integer task_progress; // 0-100%
    private String task_priority; // Urgent, Critical, High, Regular, Low, Routine, Optional
    private Long task_project_id; // project table needed
    // attachments can be added

    @ManyToMany
    @JoinTable(name = "task_assigned_to_employees", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "emp_id"))
    private Set<Employees> task_assigned_to_employees;

    @ManyToOne
    @JoinColumn(name = "task_assigned_by_emp_id", referencedColumnName = "emp_id")
    private Employees task_assigned_by_employee;

    @OneToOne
    @JoinColumn(name = "task_chat_id", referencedColumnName = "chat_id")
    @Nullable
    private Chats task_chat;
}
