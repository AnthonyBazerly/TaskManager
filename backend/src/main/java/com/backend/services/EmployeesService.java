package com.backend.services;

import com.backend.dtos.EmployeesDto;
import com.backend.models.Employees;
import com.backend.repos.EmployeesRepo;
import com.backend.mappers.EmployeesMapper;
import com.backend.repos.JobsRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    private final EmployeesMapper employeesMapper;
    private final EmployeesRepo employeesRepo;
    private final JobsRepo jobsRepo;

    public EmployeesService(EmployeesRepo employeesRepo, JobsRepo jobsRepo, EmployeesMapper employeesMapper) {
        this.employeesRepo = employeesRepo;
        this.jobsRepo = jobsRepo;
        this.employeesMapper = employeesMapper;
    }

    public List<EmployeesDto> getAllEmployees() {
        return employeesRepo.findAll().stream()
                .map(employeesMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeesDto getEmployeeById(Long id) {
        return employeesRepo.findById(id)
                .map(employeesMapper::toDto)
                .orElse(null);
    }

    public EmployeesDto createEmployee(EmployeesDto EmployeeDto) {
        Employees Employee = employeesMapper.toEntity(EmployeeDto, employeesRepo, jobsRepo);
        Employees saved = employeesRepo.save(Employee);
        return employeesMapper.toDto(saved);
    }

    public EmployeesDto updateEmployee(Long id, EmployeesDto EmployeeDto) {
        return employeesRepo.findById(id)
                .map(existing -> {
                    existing = employeesMapper.toEntity(EmployeeDto, employeesRepo, jobsRepo);
                    Employees updated = employeesRepo.save(existing);
                    return employeesMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteEmployee(Long id) {
        employeesRepo.deleteById(id);
    }
}