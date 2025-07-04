package com.backend.services;

import com.backend.dtos.JobsDto;
import com.backend.models.Jobs;
import com.backend.repos.JobTypesRepo;
import com.backend.repos.JobsRepo;
import com.backend.mappers.JobsMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobsService {
    private final JobsRepo jobsRepo;
    private final JobTypesRepo jobTypesRepo;
    private final JobsMapper jobsMapper;

    public JobsService(JobsRepo jobsRepo, JobTypesRepo jobTypesRepo, JobsMapper jobsMapper) {
        this.jobsRepo = jobsRepo;
        this.jobTypesRepo = jobTypesRepo;
        this.jobsMapper = jobsMapper;
    }

    public List<JobsDto> getAllJobs() {
        return jobsRepo.findAll().stream()
                .map(jobsMapper::toDto)
                .collect(Collectors.toList());
    }

    public JobsDto getJobById(Long id) {
        return jobsRepo.findById(id)
                .map(jobsMapper::toDto)
                .orElse(null);
    }

    public JobsDto getJobByName(String job) {
        return jobsRepo.getJobByName(job)
                .map(jobsMapper::toDto)
                .orElse(null);
    }

    public List<JobsDto> getJobsByName(String name) {
        return jobsRepo.findAll().stream()
                .filter(job -> job.getJobName().toLowerCase().contains(name.toLowerCase()))
                .map(jobsMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<JobsDto> getJobsByRankAndType(Integer rank, String type) {
        return jobsRepo.findAll().stream()
                .filter(job -> job.getJobRank() == rank - 1 && job.getJobType().getJtName().equalsIgnoreCase(type))
                .map(jobsMapper::toDto)
                .collect(Collectors.toList());
    }

    public JobsDto createJob(JobsDto jobDto) {
        Jobs job = jobsMapper.toEntity(jobDto, jobTypesRepo);
        Jobs saved = jobsRepo.save(job);
        return jobsMapper.toDto(saved);
    }

    public JobsDto updateJob(Long id, JobsDto jobDto) {
        return jobsRepo.findById(id)
                .map(existing -> {
                    existing = jobsMapper.toEntity(jobDto, jobTypesRepo);
                    Jobs updated = jobsRepo.save(existing);
                    return jobsMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteJob(Long id) {
        jobsRepo.deleteById(id);
    }
}