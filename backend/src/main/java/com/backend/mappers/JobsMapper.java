package com.backend.mappers;

import org.springframework.stereotype.Component;
import com.backend.dtos.JobsDto;
import com.backend.models.Jobs;
import com.backend.repos.JobTypesRepo;
import com.backend.models.JobTypes;

@Component
public class JobsMapper {
    public Jobs toEntity(JobsDto dto, JobTypesRepo jobtypesRepo) {
        if (dto == null) {
            return null;
        }
        Jobs entity = new Jobs();
        entity.setJobName(dto.getJobName());
        entity.setJobRank(dto.getJobRank());
        Long id = dto.getJobJtId();
        if (id == null) {
            throw new IllegalArgumentException("Job type ID is null");
        }
        entity.setJobType(jobtypesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job type not found")));
        return entity;
    }

    public JobsDto toDto(Jobs entity) {
        if (entity == null) {
            return null;
        }
        JobTypes jobtype = entity.getJobType();
        if (jobtype == null) {
            throw new IllegalArgumentException("Job type not found");
        }
        JobsDto dto = new JobsDto();
        dto.setJobName(entity.getJobName());
        dto.setJobRank(entity.getJobRank());
        dto.setJobJtId(jobtype.getJtId());
        return dto;
    }
}
