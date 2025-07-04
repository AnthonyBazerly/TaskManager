package com.backend.controllers;

import com.backend.dtos.LoginDto;
import com.backend.dtos.EmployeesDto;
import com.backend.jwt.JwtUtil;
import com.backend.services.EmployeesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("EmployeesController")
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
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest) {
        List<EmployeesDto> emps = service.getAllEmployees();
        System.out.println("Employees: " + emps);
        String encodedpwd = "";
        for (EmployeesDto emp : emps) {
            encodedpwd = emp.getEmpPassword();
            System.out.println("Employee: " + emp + ", Password: " + encodedpwd);
        }
        
        System.out.printf("request: %s%n", loginRequest);
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        System.out.printf("email: %s, password: %s%n", email, password);
        System.out.printf("check function: %b%n", service.checkPassword(password, encodedpwd));

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            System.out.printf("Authentication failed: %s%n", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String token = jwtUtil.generateToken(email);
        System.out.printf("Generated token: %s%n", token);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        System.out.printf("Response: %s%n", response);
        return ResponseEntity.ok(response);
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