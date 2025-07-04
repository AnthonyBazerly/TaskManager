package com.backend.repos;

import com.backend.models.Jobs;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepo extends JpaRepository<Jobs, Long> {
    public Optional<Jobs> findByJobName(String jobName);

    public List<Jobs> findByJobRankAndJobType_JtName(Integer jobRank, String jtName);
}
