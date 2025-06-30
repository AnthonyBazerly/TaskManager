package com.backend.models;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emp_id;
    private String emp_first_name;
    private String emp_last_name;
    private String emp_email;
    private String emp_phone_number;
    private String emp_hire_date;
    private Long emp_salary;
    @ManyToOne
    @JoinColumn(name = "emp_mng_id", referencedColumnName = "emp_id", nullable = true)
    @Nullable
    private Employees emp_manager;

    @ManyToOne
    @JoinColumn(name = "emp_job_id", referencedColumnName = "job_id")
    private Jobs emp_job;
}
