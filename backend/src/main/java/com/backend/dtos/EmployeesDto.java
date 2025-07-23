package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeesDto {
    private String empFirstName;
    private String empLastName;
    private String empEmail;
    private String empPassword;
    private String empPhoneNumber;
    private String empHireDate;
    private Long empJobId;
    private String empJob;
    private Long empSalary;
    private Long empMngId;
    private String empMng;
}
