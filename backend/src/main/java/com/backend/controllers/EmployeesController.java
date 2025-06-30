package com.backend.controllers;

import com.backend.dtos.EmployeesDto;
import com.backend.services.EmployeesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Employee")
public class EmployeesController {
    private final EmployeesService service;

    public EmployeesController(EmployeesService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeesDto> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id:[0-9]+}")
    public EmployeesDto getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PostMapping
    public EmployeesDto createEmployee(@RequestBody EmployeesDto dto) {
        return service.createEmployee(dto);
    }

    @PutMapping("/{id:[0-9]+}")
    public EmployeesDto updateEmployee(@PathVariable Long id, @RequestBody EmployeesDto dto) {
        return service.updateEmployee(id, dto);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}