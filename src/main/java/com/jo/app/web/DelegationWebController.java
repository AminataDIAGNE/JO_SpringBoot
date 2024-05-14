package com.jo.app.web;

import com.jo.app.dto.DelegationDto;
import com.jo.app.service.DelegationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisateur")
public class DelegationWebController {

    private final DelegationService delegationService;

    public DelegationWebController(DelegationService delegationService) {
        this.delegationService = delegationService;
    }

    @GetMapping("/delegations")
    public String AllDelegations(Model model) {
    	model.addAttribute("delegations", delegationService.findAllDelegations());
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
        DelegationDto delegationDto = delegationService.findDelegationById(id);
        model.addAttribute("delegation",delegationDto);
        model.addAttribute("participants", delegationDto.getParticipants());
        return "organisateur/details_delegation";
    }
}
