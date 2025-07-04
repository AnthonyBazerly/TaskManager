package com.backend.mappers;

import org.springframework.stereotype.Component;
import com.backend.dtos.EmployeesDto;
import com.backend.models.Employees;
import com.backend.models.Jobs;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.JobsRepo;

@Component
public class EmployeesMapper {
    public Employees toEntity(EmployeesDto dto, EmployeesRepo employeesRepo, JobsRepo jobsRepo) {
        if (dto == null) {
            return null;
        }
        Employees entity = new Employees();
        entity.setEmpFirstName(dto.getEmpFirstName());
        entity.setEmpLastName(dto.getEmpLastName());
        entity.setEmpEmail(dto.getEmpEmail());
        entity.setEmpPassword(dto.getEmpPassword());
        entity.setEmpPhoneNumber(dto.getEmpPhoneNumber());
        entity.setEmpHireDate(dto.getEmpHireDate());
        Long id = dto.getEmpJobId();
        if (id == null) {
            throw new IllegalArgumentException("Job ID is null");
        }
        Jobs job = jobsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        entity.setEmpJob(job);
        entity.setEmpSalary(dto.getEmpSalary());
        id = dto.getEmpMngId();
        entity.setEmpManager(id == null ? null
                : employeesRepo.findById(dto.getEmpMngId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid manager id")));
        return entity;
    }

    public EmployeesDto toDto(Employees entity) {
        if (entity == null) {
            return null;
        }
        Employees manager = entity.getEmpManager();
        Jobs job = entity.getEmpJob();
        if (job == null) {
            throw new IllegalArgumentException("Job not found");
        }
        EmployeesDto dto = new EmployeesDto();
        dto.setEmpFirstName(entity.getEmpFirstName());
        dto.setEmpLastName(entity.getEmpLastName());
        dto.setEmpEmail(entity.getEmpEmail());
        dto.setEmpPassword(entity.getEmpPassword());
        dto.setEmpPhoneNumber(entity.getEmpPhoneNumber());
        dto.setEmpHireDate(entity.getEmpHireDate());
        dto.setEmpJobId(job.getJobId());
        dto.setEmpSalary(entity.getEmpSalary());
        dto.setEmpMngId(manager != null ? manager.getEmpId() : null);
        return dto;
    }
}
