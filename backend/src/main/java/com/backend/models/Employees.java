package com.backend.models;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    @Nullable
    private Integer emp_mng_id = null;

    @ManyToOne
    @JoinColumn(name = "emp_job_id", referencedColumnName = "job_id")
    private Jobs emp_job;
}
