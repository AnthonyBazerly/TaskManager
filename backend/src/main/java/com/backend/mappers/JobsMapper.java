package com.backend.mappers;

import com.backend.dtos.JobsDto;
import com.backend.models.Jobs;

public class JobsMapper {
    public static Jobs toEntity(JobsDto dto) {
        if (dto == null) {
            return null;
        }
        Jobs entity = new Jobs();
        entity.setJob_name(dto.getJob_name());
        return entity;
    }

    public static JobsDto toDto(Jobs entity) {
        if (entity == null) {
            return null;
        }
        JobsDto dto = new JobsDto();
        dto.setJob_name(entity.getJob_name());
        return dto;
    }
}
