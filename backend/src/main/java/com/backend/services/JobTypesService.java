package com.backend.services;

import com.backend.models.JobTypes;
import com.backend.dtos.JobTypesDto;
import com.backend.repos.JobTypesRepo;
import com.backend.mappers.JobTypesMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobTypesService {
    private final JobTypesRepo jobTypesRepo;
    private final JobTypesMapper jobTypesMapper;

    public JobTypesService(JobTypesRepo jobTypesRepo, JobTypesMapper jobTypesMapper) {
        this.jobTypesRepo = jobTypesRepo;
        this.jobTypesMapper = jobTypesMapper;
    }

    public List<JobTypesDto> getAllJobTypes() {
        return jobTypesRepo.findAll().stream()
                .map(jobTypesMapper::toDto)
                .collect(Collectors.toList());
    }

    public JobTypesDto getJobTypeById(Long id) {
        return jobTypesRepo.findById(id)
                .map(jobTypesMapper::toDto)
                .orElse(null);
    }

    public JobTypesDto createJobType(JobTypesDto dto) {
        JobTypes jobtype = jobTypesMapper.toEntity(dto);
        JobTypes saved = jobTypesRepo.save(jobtype);
        return jobTypesMapper.toDto(saved);
    }

    public JobTypesDto updateJobType(Long id, JobTypesDto jobDto) {
        return jobTypesRepo.findById(id)
                .map(existing -> {
                    existing = jobTypesMapper.toEntity(jobDto);
                    JobTypes updated = jobTypesRepo.save(existing);
                    return jobTypesMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteJobType(Long id) {
        jobTypesRepo.deleteById(id);
    }
}