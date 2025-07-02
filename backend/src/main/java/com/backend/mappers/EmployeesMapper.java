package com.backend.mappers;

import com.backend.dtos.EmployeesDto;
import com.backend.models.Employees;
import com.backend.models.Jobs;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.JobsRepo;

public class EmployeesMapper {
    public Employees toEntity(EmployeesDto dto, EmployeesRepo employeesRepo, JobsRepo jobsRepo) {
        if (dto == null) {
            return null;
        }
        Employees entity = new Employees();
        entity.setEmp_first_name(dto.getEmp_first_name());
        entity.setEmp_last_name(dto.getEmp_last_name());
        entity.setEmp_email(dto.getEmp_email());
        entity.setEmp_password(dto.getEmp_password());
        entity.setEmp_phone_number(dto.getEmp_phone_number());
        entity.setEmp_hire_date(dto.getEmp_hire_date());
        Long id = dto.getEmp_job_id();
        if (id == null) {
            throw new IllegalArgumentException("Job ID is null");
        }
        Jobs job = jobsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        entity.setEmp_job(job);
        entity.setEmp_salary(dto.getEmp_salary());
        id = dto.getEmp_mng_id();
        entity.setEmp_manager(id == null ? null
                : employeesRepo.findById(dto.getEmp_mng_id())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid manager id")));
        return entity;
    }

    public EmployeesDto toDto(Employees entity) {
        if (entity == null) {
            return null;
        }
        Employees manager = entity.getEmp_manager();
        Jobs job = entity.getEmp_job();
        if (job == null) {
            throw new IllegalArgumentException("Job not found");
        }
        EmployeesDto dto = new EmployeesDto();
        dto.setEmp_first_name(entity.getEmp_first_name());
        dto.setEmp_last_name(entity.getEmp_last_name());
        dto.setEmp_email(entity.getEmp_email());
        dto.setEmp_password(entity.getEmp_password());
        dto.setEmp_phone_number(entity.getEmp_phone_number());
        dto.setEmp_hire_date(entity.getEmp_hire_date());
        dto.setEmp_job_id(job.getJob_id());
        dto.setEmp_salary(entity.getEmp_salary());
        dto.setEmp_mng_id(manager != null ? manager.getEmp_id() : null);
        return dto;
    }
}
