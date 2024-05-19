package com.jo.app.web;

import com.jo.app.dto.ParticipantDto;
import com.jo.app.dto.ResultatDto;
import com.jo.app.mapper.DelegationMapper;
import com.jo.app.service.DelegationService;
import com.jo.app.service.ParticipantService;
import com.jo.app.service.ResultatService;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisateur")
public class ParticipantWebController {

    private final ParticipantService participantService;
    
    private final DelegationService delegationService;
    
    private final ResultatService resultatervice;

    public ParticipantWebController(ParticipantService participantService, ResultatService resultatervice, DelegationService delegationService) {
        this.participantService = participantService;
        this.resultatervice = resultatervice;
        this.delegationService = delegationService;
    }
    
    @GetMapping("/all-participants")
    public String allParticipants(Model model) {
    	model.addAttribute("participants",  participantService.findAllParticipants());
    	return "organisateur/all_participant";
    }

    @GetMapping("/participants")
    public String allParticipants(Model model, Long id) {
    	ParticipantDto participantDto = participantService.findParticipantById(id);
    	model.addAttribute("participant", participantDto);
    	if(participantDto != null) {
        	model.addAttribute("resultats", resultatervice.findResultatsByParticipant(participantDto.getId()));
    	}else {
        	model.addAttribute("resultats", new ArrayList<ResultatDto>());
    	}
    	
    	return "organisateur/participant";
    }

    @PostMapping("/participants/new")
    public String save(ParticipantDto participantDto)
    {
    	participantDto.setDelegation(DelegationMapper.mapToDelegation(delegationService.findDelegationById(participantDto.getIdDelegation())));
    	participantService.createParticipant(participantDto);
        return "redirect:/organisateur/delegations/details?id="+participantDto.getIdDelegation();
    }
    
    @PostMapping("/participants/edit")
    public String update(ParticipantDto participantDto)
    {
    	participantService.updateParticipant(participantDto);
        return "redirect:/organisateur/participants";
    }
    
    @GetMapping(value="/all-participants/delete")
    public String delete(Long id)
    {
    	
    	participantService.deleteParticipant(id);
        //return "redirect:/organisateur/delegations/details?id="+p.getDelegation().getId();
    	return "redirect:/organisateur/all-participants";
    }
    
    @GetMapping(value="/participants/delete")
    public String deleteInAllParticipants(Long id)
    {
    	ParticipantDto p = participantService.findParticipantById(id);
    	participantService.deleteParticipant(id);
        return "redirect:/organisateur/delegations/details?id="+p.getDelegation().getId();
   
    }
    
    @GetMapping("/participants/findOne")
    @ResponseBody
    public Object[] findOne(Long id) {
        Object[] objects = new Object[1];
        ParticipantDto participantDto = participantService.findParticipantById(id);
        objects[0]=participantDto;
        return objects;
    }
    

    @GetMapping("/participants/getOne")
    @ResponseBody
    public ParticipantDto findById(Long id)
    {
        return participantService.findParticipantById(id);
    }
    
    @GetMapping("/participants/details")
    public String detailscategories(Model model, Long id){
        ParticipantDto participantDto = participantService.findParticipantById(id);
        model.addAttribute("participant",participantDto);
        model.addAttribute("participants", participantDto.getResultats());
        return "organisateur/details_participant";
    }
}
