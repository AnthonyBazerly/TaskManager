package com.backend.models;

import java.time.LocalDateTime;
import java.util.Set;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    private String chatName;
    private String chatStatus; // pinned, active, archived
    private LocalDateTime chatCreationDate;
    @Nullable
    private LocalDateTime chatUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "chatTaskId", referencedColumnName = "taskId")
    @Nullable
    private Tasks chatTask;

    @ManyToOne
    @JoinColumn(name = "chatCreatedByEmpId", referencedColumnName = "empId")
    private Employees chatCreatedByEmployee;

    @ManyToMany
    @JoinTable(name = "chatEmployees", joinColumns = @JoinColumn(name = "chatId"), inverseJoinColumns = @JoinColumn(name = "empId"))
    private Set<Employees> chatEmployees;
}
