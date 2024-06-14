package com.jo.app.controller;

import com.jo.app.dto.DelegationDto;
import com.jo.app.service.DelegationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/delegations", produces = "application/json")
public class DelegationController {

    private final DelegationService delegationService;

    public DelegationController(DelegationService delegationService) {
        this.delegationService = delegationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DelegationDto> getAllDelegations() {
        return delegationService.findAllDelegations();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DelegationDto getDelegationById(@PathVariable("id") Long delegationId) {
        return delegationService.findDelegationById(delegationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDelegation(@RequestBody DelegationDto delegationDto) {
        delegationService.createDelegation(delegationDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDelegation(@PathVariable("id") Long delegationId, @RequestBody DelegationDto delegationDto) {
        delegationDto.setId(delegationId);
        delegationService.updateDelegation(delegationDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDelegation(@PathVariable("id") Long delegationId) {
        delegationService.deleteDelegation(delegationId);
    }
}
