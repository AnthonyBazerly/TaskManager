package com.backend.services;

import com.backend.dtos.EmployeesDto;
import com.backend.models.Employees;
import com.backend.repos.EmployeesRepo;
import com.backend.mappers.EmployeesMapper;
import com.backend.repos.JobsRepo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    private final EmployeesMapper employeesMapper;
    private final EmployeesRepo employeesRepo;
    private final JobsRepo jobsRepo;
    private final PasswordEncoder passwordEncoder;

    public EmployeesService(EmployeesRepo employeesRepo, JobsRepo jobsRepo, EmployeesMapper employeesMapper,
            PasswordEncoder passwordEncoder) {
        this.employeesRepo = employeesRepo;
        this.jobsRepo = jobsRepo;
        this.employeesMapper = employeesMapper;
        this.passwordEncoder = passwordEncoder;
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

    public EmployeesDto getEmployeeByEmail(String email) {
        Employees employee = employeesRepo.findByEmail(email);
        return employee != null ? employeesMapper.toDto(employee) : null;
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public EmployeesDto createEmployee(EmployeesDto EmployeeDto) {
        Employees Employee = employeesMapper.toEntity(EmployeeDto, employeesRepo, jobsRepo);
        String rawPassword = Employee.getEmpPassword();
        Employee.setEmpPassword(passwordEncoder.encode(rawPassword));
        Employees saved = employeesRepo.save(Employee);
        System.out.println("Generated password for employee: " + rawPassword);
        return employeesMapper.toDto(saved);
    }

    public EmployeesDto updateEmployee(Long id, EmployeesDto EmployeeDto) {
        return employeesRepo.findById(id)
                .map(existing -> {
                    Employees updatedEntity = employeesMapper.toEntity(EmployeeDto, employeesRepo, jobsRepo);
                    updatedEntity.setEmpPassword(passwordEncoder.encode(updatedEntity.getEmpPassword()));
                    Employees updated = employeesRepo.save(updatedEntity);
                    return employeesMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteEmployee(Long id) {
        employeesRepo.deleteById(id);
    }
}