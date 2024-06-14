package com.jo.app.controller;

import com.jo.app.dto.EpreuveDto;
import com.jo.app.service.EpreuveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/epreuves", produces = "application/json")
public class EpreuveController {

    private final EpreuveService epreuveService;

    public EpreuveController(EpreuveService epreuveService) {
        this.epreuveService = epreuveService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EpreuveDto> getAllEpreuves() {
        return epreuveService.findAllEpreuves();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EpreuveDto getEpreuveById(@PathVariable("id") Long epreuveId) {
        return epreuveService.findEpreuveById(epreuveId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEpreuve(@RequestBody EpreuveDto epreuveDto) {
    	
    	if(epreuveDto.getNombrePlacesMisesEnVente() > epreuveDto.getInfrastructureSportive().getCapacite()) {
    		 throw new RuntimeException("Le nombre de place mise en vente ne doit pas etre supérieur à la capacité de l'infrastructure sportive");
    	}
        epreuveService.createEpreuve(epreuveDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEpreuve(@PathVariable("id") Long epreuveId, @RequestBody EpreuveDto epreuveDto) {
    	if(epreuveDto.getNombrePlacesMisesEnVente() > epreuveDto.getInfrastructureSportive().getCapacite()) {
   		 	throw new RuntimeException("Le nombre de place mise en vente ne doit pas etre supérieur à la capacité de l'infrastructure sportive");
    	}
        epreuveDto.setId(epreuveId);
        epreuveService.updateEpreuve(epreuveDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEpreuve(@PathVariable("id") Long epreuveId) {
        epreuveService.deleteEpreuve(epreuveId);
    }

    @PutMapping("/{participantId}/inscrire/{epreuveId}")
    @ResponseStatus(HttpStatus.OK)
    public EpreuveDto inscrireParticipant(@PathVariable Long participantId, @PathVariable Long epreuveId) {
        return epreuveService.inscrireParticipant(epreuveId, participantId);
    }

    @DeleteMapping("/{participantId}/desinscrire/{epreuveId}")
    @ResponseStatus(HttpStatus.OK)
    public void desinscrireParticipant(@PathVariable Long participantId, @PathVariable Long epreuveId) {
        epreuveService.desinscrireParticipant(epreuveId, participantId);
    }

}
