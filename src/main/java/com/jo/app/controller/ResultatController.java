package com.jo.app.controller;

import com.jo.app.dto.DelegationDto;
import com.jo.app.dto.ResultatDto;
import com.jo.app.service.DelegationService;
import com.jo.app.service.ResultatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/resultats", produces = "application/json")
public class ResultatController {

    private ResultatService resultatService;

    public ResultatController(ResultatService resultatService, DelegationService delegationService) {
        this.resultatService = resultatService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResultatDto> getAllResultats(){
        return resultatService.findAllResultats();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultatDto getResultatById(@PathVariable Long id){
        return resultatService.findResultatById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createResultat(@RequestBody @Valid ResultatDto resultatDto){
        resultatService.createResultat(resultatDto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateResultat(@PathVariable Long id, @RequestBody ResultatDto resultatDto){
        resultatDto.setId(id);
        resultatService.updateResultat(resultatDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResultat(@PathVariable Long id){
        resultatService.deleteResultat(id);
    }

    @GetMapping("/classement-general")
    @ResponseStatus(HttpStatus.OK)
    public List<DelegationDto> getClassementGeneral() {
        return resultatService.calculerClassementGeneral();
    }
}
