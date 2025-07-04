package com.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobsDto {
    private String jobName;
    private Integer jobRank;
    private Long jobJtId;
}
