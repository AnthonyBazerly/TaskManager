package com.backend.mappers;

import com.backend.dtos.JobsDto;
import com.backend.models.Jobs;
import com.backend.repos.JobTypesRepo;
import com.backend.models.JobTypes;

public class JobsMapper {
    public Jobs toEntity(JobsDto dto, JobTypesRepo jobtypesRepo) {
        if (dto == null) {
            return null;
        }
        Jobs entity = new Jobs();
        entity.setJob_name(dto.getJob_name());
        entity.setJob_rank(dto.getJob_rank());
        Long id = dto.getJob_jt_id();
        if (id == null) {
            throw new IllegalArgumentException("Job type ID is null");
        }
        entity.setJob_type(jobtypesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job type not found")));
        return entity;
    }

    public JobsDto toDto(Jobs entity) {
        if (entity == null) {
            return null;
        }
        JobTypes jobtype = entity.getJob_type();
        if (jobtype == null) {
            throw new IllegalArgumentException("Job type not found");
        }
        JobsDto dto = new JobsDto();
        dto.setJob_name(entity.getJob_name());
        dto.setJob_rank(entity.getJob_rank());
        dto.setJob_jt_id(jobtype.getJt_id());
        return dto;
    }
}
