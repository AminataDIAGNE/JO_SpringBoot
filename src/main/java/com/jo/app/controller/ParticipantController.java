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
import com.jo.app.dto.ParticipantDto;
import com.jo.app.mapper.DelegationMapper;
import com.jo.app.service.DelegationService;
import com.jo.app.service.ParticipantService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/v1/participants", produces = "application/json")
public class ParticipantController {

    private ParticipantService participantService;
    private DelegationService delegationService;

    public ParticipantController(ParticipantService participantService, DelegationService delegationService) {
        this.participantService = participantService;
        this.delegationService = delegationService;
    }


    @GetMapping
    public List<ParticipantDto> getAllParticipants(){
        return participantService.findAllParticipants();
    }

    @GetMapping("/nom/{nom}")
    public List<ParticipantDto> getParticipantsByNom(@PathVariable String nom){
        return participantService.findAllParticipantsByNom(nom);
    }

    @GetMapping("/{id}")
    public ParticipantDto getParticipantById(@PathVariable Long id){
        return participantService.findParticipantById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createParticipant(@RequestBody @Valid ParticipantDto participantDto){
        participantService.createParticipant(participantDto);
    }
    
    @PostMapping("/{idDelegation}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createParticipant(@RequestBody @Valid ParticipantDto participantDto, @PathVariable Long idDelegation){
    	try {
    		DelegationDto delegationDto = delegationService.findDelegationById(idDelegation);
    		participantDto.setDelegation(DelegationMapper.mapToDelegation(delegationDto));
    		participantService.createParticipant(participantDto);
		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Delegation Introuvable", e);
		}
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateParticipant(@PathVariable Long id, @RequestBody ParticipantDto participantDto){
        participantDto.setId(id);
        participantService.updateParticipant(participantDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParticipant(@PathVariable Long id){
        participantService.deleteParticipant(id);
    }

    @GetMapping("/delegation")
    public List<ParticipantDto> getParticipantsByNomAndDelegation(@RequestParam String nom, @RequestParam Long delegationId){
        return participantService.findParticipantsByNomAndDelegation(nom, delegationId);
    }

    @GetMapping("/search")
    public List<ParticipantDto> searchParticipantsByPartialNom(@RequestParam String partialNom){
        return participantService.findParticipantsByNomContaining(partialNom);
    }

    @GetMapping("/sorted")
    public List<ParticipantDto> getParticipantsOrderedByName(){
        return participantService.findAllParticipantsOrderedByName();
    }

    @GetMapping("/count")
    public long countParticipantsByDelegationId(@RequestParam Long delegationId){
        return participantService.countParticipantsByDelegationId(delegationId);
    }

    @GetMapping("/exists")
    public boolean existsParticipantByNom(@RequestParam String nom){
        return participantService.existsParticipantByNom(nom);
    }

    @GetMapping("/existsInDelegation")
    public boolean existsParticipantByNomAndDelegationId(@RequestParam String nom, @RequestParam Long delegationId){
        return participantService.existsParticipantByNomAndDelegationId(nom, delegationId);
    }
}
