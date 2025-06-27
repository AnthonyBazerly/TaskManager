package com.backend.services;

import com.backend.dtos.EmployeesDto;
import com.backend.models.Employees;
import com.backend.repos.EmployeesRepo;
import com.backend.mappers.EmployeesMapper;
import com.backend.models.Jobs;
import com.backend.repos.JobsRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    private final EmployeesRepo EmployeeRepo;
    private final JobsRepo jobsRepo;

    public EmployeesService(EmployeesRepo EmployeeRepo, JobsRepo jobsRepo) {
        this.EmployeeRepo = EmployeeRepo;
        this.jobsRepo = jobsRepo;
    }

    public List<EmployeesDto> getAllEmployees() {
        return EmployeeRepo.findAll().stream()
                .map(EmployeesMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeesDto getEmployeeById(Long id) {
        return EmployeeRepo.findById(id)
                .map(EmployeesMapper::toDto)
                .orElse(null);
    }

    public EmployeesDto createEmployee(EmployeesDto EmployeeDto) {
        Employees Employee = EmployeesMapper.toEntity(EmployeeDto, jobsRepo);
        Employees saved = EmployeeRepo.save(Employee);
        return EmployeesMapper.toDto(saved);
    }

    public EmployeesDto updateEmployee(Long id, EmployeesDto EmployeeDto) {
        Jobs job = jobsRepo.findById(EmployeeDto.getEmp_job_id()).orElse(null);
        return EmployeeRepo.findById(id)
                .map(existing -> {
                    existing.setEmp_first_name(EmployeeDto.getEmp_first_name());
                    existing.setEmp_last_name(EmployeeDto.getEmp_last_name());
                    existing.setEmp_email(EmployeeDto.getEmp_email());
                    existing.setEmp_phone_number(EmployeeDto.getEmp_phone_number());
                    existing.setEmp_hire_date(EmployeeDto.getEmp_hire_date());
                    existing.setEmp_job(job);
                    existing.setEmp_salary(EmployeeDto.getEmp_salary());
                    existing.setEmp_mng_id(EmployeeDto.getEmp_mng_id());
                    Employees updated = EmployeeRepo.save(existing);
                    return EmployeesMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteEmployee(Long id) {
        EmployeeRepo.deleteById(id);
    }
}