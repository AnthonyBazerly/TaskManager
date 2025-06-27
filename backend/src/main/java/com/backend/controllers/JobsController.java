package com.backend.controllers;

import com.backend.dtos.JobsDto;
import com.backend.services.JobsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Job")
public class JobsController {
    private final JobsService service;

    public JobsController(JobsService service) {
        this.service = service;
    }

    @GetMapping
    public List<JobsDto> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobsDto getJobById(@PathVariable Long id) {
        return service.getJobById(id);
    }

    @GetMapping("/{name}")
    public JobsDto getJobByName(@PathVariable String name) {
        return service.getJobByName(name);
    }

    @PostMapping
    public JobsDto createJob(@RequestBody JobsDto dto) {
        return service.createJob(dto);
    }

    @PutMapping("/{id}")
    public JobsDto updateJob(@PathVariable Long id, @RequestBody JobsDto dto) {
        return service.updateJob(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        service.deleteJob(id);
    }
}