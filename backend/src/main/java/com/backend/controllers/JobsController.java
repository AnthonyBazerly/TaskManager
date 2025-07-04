package com.backend.controllers;

import com.backend.dtos.JobsDto;
import com.backend.services.JobsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("JobsController")
@RequestMapping("/api/jobs")
public class JobsController {
    private final JobsService service;

    public JobsController(JobsService service) {
        this.service = service;
    }

    @GetMapping
    public List<JobsDto> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/{id:[0-9]+}")
    public JobsDto getJobById(@PathVariable Long id) {
        return service.getJobById(id);
    }

    @GetMapping("/name/{name}")
    public JobsDto getJobByName(@PathVariable String name) {
        return service.getJobByName(name);
    }

    @GetMapping("/names/{name}")
    public List<JobsDto> getJobsByName(@PathVariable String name) {
        return service.getJobsByName(name);
    }

    @GetMapping("/rank_type/{rank:[1-6]}/{type}")
    public List<JobsDto> getJobByRank(@PathVariable Integer rank, @PathVariable String type) {
        return service.getJobsByRankAndType(rank, type);
    }

    @PostMapping
    public JobsDto createJob(@RequestBody JobsDto dto) {
        return service.createJob(dto);
    }

    @PutMapping("/{id:[0-9]+}")
    public JobsDto updateJob(@PathVariable Long id, @RequestBody JobsDto dto) {
        return service.updateJob(id, dto);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void deleteJob(@PathVariable Long id) {
        service.deleteJob(id);
    }
}