package com.backend.repos;

import com.backend.models.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JobsRepo extends JpaRepository<Jobs, Long> {
    Optional<Jobs> findByJob(String job);
}
