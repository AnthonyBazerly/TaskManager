package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobsDto {
    private String job_name;
    private Integer job_rank;
    private Long job_jt_id;
}
