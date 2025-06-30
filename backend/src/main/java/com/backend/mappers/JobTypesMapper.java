package com.backend.mappers;

import com.backend.dtos.JobTypesDto;
import com.backend.models.JobTypes;

public class JobTypesMapper {
    public JobTypes toEntity(JobTypesDto dto) {
        if (dto == null) {
            return null;
        }
        JobTypes entity = new JobTypes();
        entity.setJt_name(dto.getJt_name());
        return entity;
    }

    public JobTypesDto toDto(JobTypes entity) {
        if (entity == null) {
            return null;
        }
        JobTypesDto dto = new JobTypesDto();
        dto.setJt_name(entity.getJt_name());
        return dto;
    }
}
