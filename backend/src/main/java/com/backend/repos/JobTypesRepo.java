package com.backend.repos;

import com.backend.models.JobTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypesRepo extends JpaRepository<JobTypes, Long> {
}
