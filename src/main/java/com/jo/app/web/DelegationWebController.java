package com.jo.app.web;

import com.jo.app.dto.DelegationDto;
import com.jo.app.dto.ParticipantDto;
import com.jo.app.service.DelegationService;
import com.jo.app.service.ParticipantService;
import com.jo.app.util.SecurityUtils;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisateur")
public class DelegationWebController {

    private final DelegationService delegationService;
    private final ParticipantService participantService;

    public DelegationWebController(DelegationService delegationService, ParticipantService participantService) {
        this.delegationService = delegationService;
        this.participantService = participantService;
    }

    @GetMapping("/delegations")
    public String AllDelegations(Model model) {
    	User user = SecurityUtils.getCurrentUser();
    	List<String> acces = SecurityUtils.getRoles();
    	if(acces.contains("ROLE_ORGANISATEUR")) {
    		model.addAttribute("delegations", delegationService.findAllDelegations());
    	}else if(acces.contains("ROLE_PARTICIPANT")) {
    		ParticipantDto partDto = participantService.findParticipantByEmail(user.getUsername());
    		model.addAttribute("delegations", Arrays.asList(partDto.getDelegation()));
    	}
    	
    	model.addAttribute("acces", SecurityUtils.getRoles());
    	model.addAttribute("username", user.getUsername());
    	return "organisateur/delegation";
    }

    @PostMapping("/delegations/new")
    public String save(DelegationDto delegationDto)
    {
    	delegationService.createDelegation(delegationDto);
        return "redirect:/organisateur/delegations";
    }
    
    @PostMapping("/delegations/edit")
    public String update(DelegationDto delegationDto)
    {
    	delegationService.updateDelegation(delegationDto);
        return "redirect:/organisateur/delegations";
    }
    
    @GetMapping(value="/delegations/delete")
    public String delete(Long id)
    {
    	delegationService.deleteDelegation(id);
        return "redirect:/organisateur/delegations";
    }
    
    @GetMapping("/delegations/findOne")
    @ResponseBody
    public Object[] findOne(Long id) {
        Object[] objects = new Object[1];
        DelegationDto delegationDto = delegationService.findDelegationById(id);
        objects[0]=delegationDto;
        return objects;
    }
    

    @GetMapping("/delegations/getOne")
    @ResponseBody
    public DelegationDto findById(Long id)
    {
        return delegationService.findDelegationById(id);
    }
    
    @GetMapping("/delegations/details")
    public String detailscategories(Model model, Long id){
    	User user = SecurityUtils.getCurrentUser();
        DelegationDto delegationDto = delegationService.findDelegationById(id);
        model.addAttribute("delegation",delegationDto);
        model.addAttribute("participants", delegationDto.getParticipants());
        model.addAttribute("acces", SecurityUtils.getRoles());
        model.addAttribute("username", user.getUsername());
        return "organisateur/details_delegation";
    }
}
