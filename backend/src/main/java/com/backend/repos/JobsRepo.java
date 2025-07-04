package com.backend.repos;

import com.backend.models.Jobs;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepo extends JpaRepository<Jobs, Long> {
    public Optional<Jobs> getJobByName(String jobName);

    public List<Jobs> getJobsByName(String name);

    public List<Jobs> getJobsByRankAndType(Integer rank, String type);
}
