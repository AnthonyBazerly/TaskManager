package com.backend.mappers;

import com.backend.dtos.EmployeesDto;
import com.backend.models.Employees;
import com.backend.models.Jobs;
import com.backend.repos.JobsRepo;

public class EmployeesMapper {
    public static Employees toEntity(EmployeesDto dto, JobsRepo jobsRepo) {
        if (dto == null) {
            return null;
        }
        Employees entity = new Employees();
        entity.setEmp_first_name(dto.getEmp_first_name());
        entity.setEmp_last_name(dto.getEmp_last_name());
        entity.setEmp_email(dto.getEmp_email());
        entity.setEmp_phone_number(dto.getEmp_phone_number());
        entity.setEmp_hire_date(dto.getEmp_hire_date());
        Jobs job = jobsRepo.findById(dto.getEmp_job_id()).orElse(null);
        entity.setEmp_job(job);
        entity.setEmp_salary(dto.getEmp_salary());
        entity.setEmp_mng_id(dto.getEmp_mng_id());
        return entity;
    }

    public static EmployeesDto toDto(Employees entity) {
        if (entity == null) {
            return null;
        }
        EmployeesDto dto = new EmployeesDto();
        dto.setEmp_first_name(entity.getEmp_first_name());
        dto.setEmp_last_name(entity.getEmp_last_name());
        dto.setEmp_email(entity.getEmp_email());
        dto.setEmp_phone_number(entity.getEmp_phone_number());
        dto.setEmp_hire_date(entity.getEmp_hire_date());
        dto.setEmp_job_id(entity.getEmp_job() != null ? entity.getEmp_job().getJob_id() : null);
        dto.setEmp_salary(entity.getEmp_salary());
        dto.setEmp_mng_id(entity.getEmp_mng_id());
        return dto;
    }
}
