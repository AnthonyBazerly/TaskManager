package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeesDto {
    private String emp_first_name;
    private String emp_last_name;
    private String emp_email;
    private String emp_password;
    private String emp_phone_number;
    private String emp_hire_date;
    private Long emp_job_id;
    private Long emp_salary;
    private Long emp_mng_id;
}
