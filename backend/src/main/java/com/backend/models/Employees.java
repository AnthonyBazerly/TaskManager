package com.backend.models;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    private String empFirstName;
    private String empLastName;
    private String empEmail;
    private String empPassword;
    private String empPhoneNumber;
    private String empHireDate;
    private Long empSalary;

    @ManyToOne
    @JoinColumn(name = "empMngId", referencedColumnName = "empId", nullable = true)
    @Nullable
    private Employees empManager;

    @ManyToOne
    @JoinColumn(name = "empJobId", referencedColumnName = "jobId")
    private Jobs empJob;
}
