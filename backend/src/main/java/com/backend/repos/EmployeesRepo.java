package com.backend.repos;

import com.backend.models.Employees;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepo extends JpaRepository<Employees, Long> {
    Employees findByEmpEmail(String email);
    Optional<Employees> findByEmpEmailIgnoreCase(String email);
}
