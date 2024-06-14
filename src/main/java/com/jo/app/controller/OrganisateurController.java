package com.jo.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jo.app.dto.OrganisateurDto;
import com.jo.app.service.OrganisateurService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/v1/organisateurs", produces = "application/json")
public class OrganisateurController {

    private OrganisateurService organisateurService;

    public OrganisateurController(OrganisateurService organisateurService) {
        this.organisateurService = organisateurService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrganisateurDto> getAllOrganisateurs(){
        return organisateurService.findAllOrganisateurs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganisateurDto getOrganisateurById(@PathVariable Long id){
        return organisateurService.findOrganisateurById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrganisateur(@RequestBody @Valid OrganisateurDto organisateurDto){
        organisateurService.createOrganisateur(organisateurDto);
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrganisateur(@PathVariable Long id, @RequestBody OrganisateurDto organisateurDto){
        organisateurDto.setId(id);
        organisateurService.updateOrganisateur(organisateurDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganisateur(@PathVariable Long id){
        organisateurService.deleteOrganisateur(id);
    }


}
