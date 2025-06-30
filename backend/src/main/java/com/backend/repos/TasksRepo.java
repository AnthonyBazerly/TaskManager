package com.backend.repos;

import com.backend.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepo extends JpaRepository<Tasks, Long> {
}
