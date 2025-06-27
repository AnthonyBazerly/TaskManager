package com.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Priorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priority_id;
    private String priority_name;
    private Integer priority_rank;
    private String priority_color;
}
