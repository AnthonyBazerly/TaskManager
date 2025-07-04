package com.backend.mappers;

import org.springframework.stereotype.Component;
import com.backend.dtos.JobTypesDto;
import com.backend.models.JobTypes;

@Component
public class JobTypesMapper {
    public JobTypes toEntity(JobTypesDto dto) {
        if (dto == null) {
            return null;
        }
        JobTypes entity = new JobTypes();
        entity.setJtName(dto.getJtName());
        return entity;
    }

    public JobTypesDto toDto(JobTypes entity) {
        if (entity == null) {
            return null;
        }
        JobTypesDto dto = new JobTypesDto();
        dto.setJtName(entity.getJtName());
        return dto;
    }
}
