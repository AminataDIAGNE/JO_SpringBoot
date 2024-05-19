package com.jo.app.controller;

import com.jo.app.dto.InfrastructureSportiveDto;
import com.jo.app.service.InfrastructureSportiveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/infrastructureSportives", produces = "application/json")
public class InfrastructureSportiveController {

    private final InfrastructureSportiveService infrastructureSportiveService;

    public InfrastructureSportiveController(InfrastructureSportiveService infrastructureSportiveService) {
        this.infrastructureSportiveService = infrastructureSportiveService;
    }

    @GetMapping
    public List<InfrastructureSportiveDto> getAllInfrastructureSportives() {
        return infrastructureSportiveService.findAllInfrastructureSportives();
    }

    @GetMapping("/{id}")
    public InfrastructureSportiveDto getInfrastructureSportiveById(@PathVariable("id") Long infrastructureSportiveId) {
        return infrastructureSportiveService.findInfrastructureSportiveById(infrastructureSportiveId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInfrastructureSportive(@RequestBody InfrastructureSportiveDto infrastructureSportiveDto) {
        infrastructureSportiveService.createInfrastructureSportive(infrastructureSportiveDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateInfrastructureSportive(@PathVariable("id") Long infrastructureSportiveId, @RequestBody InfrastructureSportiveDto infrastructureSportiveDto) {
        infrastructureSportiveDto.setId(infrastructureSportiveId);
        infrastructureSportiveService.updateInfrastructureSportive(infrastructureSportiveDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInfrastructureSportive(@PathVariable("id") Long infrastructureSportiveId) {
        infrastructureSportiveService.deleteInfrastructureSportive(infrastructureSportiveId);
    }
}
