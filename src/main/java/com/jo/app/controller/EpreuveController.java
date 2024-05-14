package com.jo.app.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jo.app.dto.DelegationDto;
import com.jo.app.dto.EpreuveDto;
import com.jo.app.mapper.DelegationMapper;
import com.jo.app.service.DelegationService;
import com.jo.app.service.EpreuveService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/v1/epreuves", produces = "application/json")
public class EpreuveController {

    private EpreuveService epreuveService;
    private DelegationService delegationService;

    public EpreuveController(EpreuveService epreuveService, DelegationService delegationService) {
        this.epreuveService = epreuveService;
        this.delegationService = delegationService;
    }


    @GetMapping
    public List<EpreuveDto> getAllEpreuves(){
        return epreuveService.findAllEpreuves();
    }

    @GetMapping("/{id}")
    public EpreuveDto getEpreuveById(@PathVariable Long id){
        return epreuveService.findEpreuveById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEpreuve(@RequestBody @Valid EpreuveDto epreuveDto){
        epreuveService.createEpreuve(epreuveDto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEpreuve(@PathVariable Long id, @RequestBody EpreuveDto epreuveDto){
        epreuveDto.setId(id);
        epreuveService.updateEpreuve(epreuveDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEpreuve(@PathVariable Long id){
        epreuveService.deleteEpreuve(id);
    }


}
