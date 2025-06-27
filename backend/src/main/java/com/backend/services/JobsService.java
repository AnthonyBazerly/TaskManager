package com.backend.services;

import com.backend.dtos.JobsDto;
import com.backend.models.Jobs;
import com.backend.repos.JobsRepo;
import com.backend.mappers.JobsMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobsService {
    private final JobsRepo jobRepo;

    public JobsService(JobsRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    public List<JobsDto> getAllJobs() {
        return jobRepo.findAll().stream()
                .map(JobsMapper::toDto)
                .collect(Collectors.toList());
    }

    public JobsDto getJobById(Long id) {
        return jobRepo.findById(id)
                .map(JobsMapper::toDto)
                .orElse(null);
    }

    public JobsDto getJobByName(String job) {
        return jobRepo.findByJob(job)
                .map(JobsMapper::toDto)
                .orElse(null);
    }

    public JobsDto createJob(JobsDto jobDto) {
        Jobs job = JobsMapper.toEntity(jobDto);
        Jobs saved = jobRepo.save(job);
        return JobsMapper.toDto(saved);
    }

    public JobsDto updateJob(Long id, JobsDto jobDto) {
        return jobRepo.findById(id)
                .map(existing -> {
                    existing.setJob_name(jobDto.getJob_name());
                    existing.setJob_name(jobDto.getJob_name());
                    Jobs updated = jobRepo.save(existing);
                    return JobsMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteJob(Long id) {
        jobRepo.deleteById(id);
    }
}