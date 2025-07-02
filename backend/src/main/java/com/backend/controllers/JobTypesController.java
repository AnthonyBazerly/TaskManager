package com.backend.controllers;

import com.backend.dtos.JobTypesDto;
import com.backend.services.JobTypesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobtypes")
public class JobTypesController {
    private final JobTypesService service;

    public JobTypesController(JobTypesService service) {
        this.service = service;
    }

    @GetMapping
    public List<JobTypesDto> getAllJobTypes() {
        return service.getAllJobTypes();
    }

    @GetMapping("/{id:[0-9]+}")
    public JobTypesDto getJobTypeById(@PathVariable Long id) {
        return service.getJobTypeById(id);
    }

    @PostMapping
    public JobTypesDto createJobType(@RequestBody JobTypesDto dto) {
        return service.createJobType(dto);
    }

    @PutMapping("/{id:[0-9]+}")
    public JobTypesDto updateJobType(@PathVariable Long id, @RequestBody JobTypesDto dto) {
        return service.updateJobType(id, dto);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void deleteJobType(@PathVariable Long id) {
        service.deleteJobType(id);
    }
}