package com.backend.models;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String task_name;
    private String task_description;
    private String task_status;
    private String task_creation_date;
    @Nullable
    private String task_due_date;
    private Long task_assigned_to_emp_id;
    private String task_creation_emp_id;
    private String task_last_updated_date; // history table needed
    private Long task_project_id; // project table needed

    @ManyToOne
    @JoinColumn(name = "task_priority_id", referencedColumnName = "priority_id")
    private Priorities task_priority;

    @ManyToOne
    @JoinColumn(name = "task_assigned_to_emp_id", referencedColumnName = "emp_id")
    private Employees task_assigned_to_employee;

    @ManyToOne
    @JoinColumn(name = "task_assigned_by_emp_id", referencedColumnName = "emp_id")
    private Employees task_assigned_by_employee;
}
