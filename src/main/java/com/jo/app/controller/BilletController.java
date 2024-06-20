package com.jo.app.controller;

import com.jo.app.dto.BilletDto;
import com.jo.app.service.BilletService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/billets", produces = "application/json")
public class BilletController {

    private final BilletService billetService;

    public BilletController(BilletService billetService) {
        this.billetService = billetService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BilletDto> getAllBillets() {
        return billetService.findAllBillets();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BilletDto getBilletById(@PathVariable("id") Long billetId) {
        return billetService.findBilletById(billetId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBillet(@RequestBody BilletDto billetDto) {
    	try {
    		billetService.createBillet(billetDto);
		} catch (RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Impossible de créer ou de modifier le billet. Nombre de billets restants insuffisant ou limite de billets par spectateur atteinte (4 billets).",
					e);
		}

    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBillet(@PathVariable("id") Long billetId, @RequestBody BilletDto billetDto) {
        try {
            billetDto.setId(billetId);
            billetService.updateBillet(billetDto);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Impossible de modifier le billet. Nombre de billets restants insuffisant ou limite de billets par spectateur atteinte (4 billets).",
                    e);
        }
    }

    @PostMapping("/reservation")
    @ResponseStatus(HttpStatus.OK)
    public BilletDto reservationBillet(@RequestParam("idSpectateur") Long idSpectateur, @RequestParam("idEpreuve") Long idEpreuve, @RequestParam("quantite") int quantite) {
        try {
            return billetService.reserverBillet(idSpectateur, idEpreuve, quantite);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Impossible de réserver le billet. Nombre de billets restants insuffisant ou limite de billets par spectateur atteinte (4 billets).",
                    e);
        }
    }

    @PostMapping("/confirmer-paiement/{id}")
    public BilletDto confirmerPaiement(@RequestBody BilletDto billetDto) {
        billetService.confirmerPaiement(billetDto.getId());
        return billetDto;
    }



    @PutMapping("/cancel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void cancelBillet(@PathVariable("id") Long billetId) {
    	try {
    		 billetService.cancelBillet(billetId);
		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID ("+billetId+") Billet Introuvable", e);
		}
    }

    @PutMapping("/controle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void controle(@PathVariable("id") Long billetId, @RequestBody BilletDto billetDto) {
        try {
            billetDto.setId(billetId);
            billetService.controle(billetDto);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Billet non valide.",
                    e);
        }
    }

    @GetMapping("/Stat")
    @ResponseStatus(HttpStatus.OK)
    public int statistique() {
            return billetService.venteStatistiques();
    }

    @GetMapping("/Stat/{idEpreuve}")
    @ResponseStatus(HttpStatus.OK)
    public int statistiqueParEpreuve(@PathVariable("id") Long epreuveId) {
        return billetService.venteStatistiquesParEpreuve(epreuveId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBillet(@PathVariable("id") Long billetId) {
        billetService.deleteBillet(billetId);
    }
}
