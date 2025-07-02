package com.backend.controllers;

import com.backend.dtos.LoginDto;
import com.backend.dtos.EmployeesDto;
import com.backend.jwt.JwtUtil;
import com.backend.services.EmployeesService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final EmployeesService service;

    public EmployeesController(EmployeesService service,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<EmployeesDto> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id:[0-9]+}")
    public EmployeesDto getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PostMapping("/login")
    public Map<String, String> login(LoginDto loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(email);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
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