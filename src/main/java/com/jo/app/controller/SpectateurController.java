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
import com.jo.app.dto.SpectateurDto;
import com.jo.app.mapper.DelegationMapper;
import com.jo.app.service.DelegationService;
import com.jo.app.service.SpectateurService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/v1/spectateurs", produces = "application/json")
public class SpectateurController {

    private SpectateurService spectateurService;
    private DelegationService delegationService;

    public SpectateurController(SpectateurService spectateurService, DelegationService delegationService) {
        this.spectateurService = spectateurService;
        this.delegationService = delegationService;
    }


    @GetMapping
    public List<SpectateurDto> getAllSpectateurs(){
        return spectateurService.findAllSpectateurs();
    }


    @GetMapping("/{id}")
    public SpectateurDto getSpectateurById(@PathVariable Long id){
        return spectateurService.findSpectateurById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSpectateur(@RequestBody @Valid SpectateurDto spectateurDto){
        spectateurService.createSpectateur(spectateurDto);
    }




    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSpectateur(@PathVariable Long id, @RequestBody SpectateurDto spectateurDto){
        spectateurDto.setId(id);
        spectateurService.updateSpectateur(spectateurDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpectateur(@PathVariable Long id){
        spectateurService.deleteSpectateur(id);
    }

}
