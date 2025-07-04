package com.backend.jwt;

import com.backend.models.Employees;
import com.backend.repos.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtDetailsService implements UserDetailsService {

    @Autowired
    private EmployeesRepo employeesRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employees employee = employeesRepo.findAll().stream()
                .filter(e -> e.getEmpEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + email));
        return org.springframework.security.core.userdetails.User
                .withUsername(employee.getEmpEmail())
                .password(employee.getEmpPassword())
                .authorities(employee.getEmpJob().getJobName())
                .build();
    }
}
