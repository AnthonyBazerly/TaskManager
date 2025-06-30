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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_id;
    private String chat_name;
    private String chat_status; // pinned, active, archived
    private LocalDateTime chat_creation_date;
    @Nullable
    private LocalDateTime chat_updated_date;

    @ManyToOne
    @JoinColumn(name = "chat_task_id", referencedColumnName = "task_id")
    @Nullable
    private Tasks chat_task;

    @ManyToOne
    @JoinColumn(name = "chat_created_by_emp_id", referencedColumnName = "emp_id")
    private Employees chat_created_by_employee;

    @ManyToMany
    @JoinTable(name = "chat_employees", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "emp_id"))
    private Set<Employees> chat_employees;
}
