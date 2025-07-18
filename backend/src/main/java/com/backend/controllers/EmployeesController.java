package com.backend.controllers;

import com.backend.dtos.EmployeesDto;
import com.backend.dtos.LoginDto;
import com.backend.jwt.JwtUtil;
import com.backend.services.EmployeesService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController("EmployeesController")
@RequestMapping("/api/employees")
public class EmployeesController {
    private final EmployeesService service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

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

    @GetMapping("/get") 
    public ResponseEntity<?> getKey() {
        return ResponseEntity.ok(Map.of("message", "ok"));
    }

    @GetMapping("/check-login")
    public ResponseEntity<?> checkLogin() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("User is logged in");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest, HttpServletRequest request) {
        System.out.println("Login attempt with email: " + loginRequest.getEmail() + 
                           " and password: " + loginRequest.getPassword());
        try{
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();
            
            try {
                System.out.println(service.checkPassword(password, "$2a$12$1lQVbGecYwug7TC6e21/uupPudi0T2YXNHEsJfj/Cm6LyeguWreAS"));
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password));
            } catch (AuthenticationException e) {
                System.out.println("Authentication failed with error: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        
            String token = jwtUtil.generateToken(email);
            ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .path("/")
                .sameSite("Strict")
                .build();
        
            EmployeesDto response = service.getEmployeeByEmail(email);
            System.out.println("User logged in: " + response.getEmpEmail() + " with token: " + token);

            return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred during login: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from("token", "")
            .httpOnly(true)
            .path("/")
            .sameSite("Strict")
            .maxAge(0)
            .build();

        SecurityContextHolder.clearContext();

        return ResponseEntity.ok()
            .header("Set-Cookie", cookie.toString())
            .body("Logout successful");
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