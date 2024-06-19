package com.jo.app.web;

import com.jo.app.dto.BilletDto;
import com.jo.app.dto.SpectateurDto;
import com.jo.app.service.BilletService;
import com.jo.app.service.SpectateurService;
import com.jo.app.util.Etat;
import com.jo.app.util.SecurityUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;

@Controller
@RequestMapping("/organisateur")
public class SpectateurWebController {

    private final SpectateurService spectateurService;
    private final BilletService billetService;

    public SpectateurWebController(SpectateurService spectateurService, BilletService billetService) {
        this.spectateurService = spectateurService;
        this.billetService = billetService;
    }
    
    @GetMapping("/spectateurs")
    public String allSpectateurs(Model model) {
    	User user = SecurityUtils.getCurrentUser();
    	SpectateurDto spectateur = new SpectateurDto();
    	List<BilletDto> billetsAVenir = null;
    	List<BilletDto> billetsPasses = null;
    	List<BilletDto> billetsAValider = null;
    	if(user != null) {
    		 spectateur = spectateurService.findByEmail(user.getUsername());
    		 
    		 // Supposons que billetService est une instance de votre service de billets
    		 List<BilletDto> billets = billetService.findAllBySpectateur(spectateur);
    		 if (billets != null) {
    			// Obtenir la date actuelle
    			 LocalDateTime now = LocalDateTime.now();

        	     // Filtrer les billets dont la date n'est pas encore arrivée
        	     billetsAVenir = billets.stream()
        	            .filter(billet -> billet.getEpreuve().getDate().isAfter(now))
        	            .collect(Collectors.toList());
        	     
        	     // Filtrer les billets à valider
        	     billetsAValider =  billetsAVenir.stream()
         	            .filter(billet -> Etat.EN_ATTENTE.equals(billet.getEtat()))
         	            .collect(Collectors.toList());
        	     
        	     // Filtrer les billets dont la date n'est pas encore arrivée et dont l'etat est à valide
        	     billetsAVenir = billetsAVenir.stream()
        	            .filter(billet -> Etat.VALIDE.equals(billet.getEtat()))
        	            .collect(Collectors.toList());

        	     // Filtrer les billets dont la date est dépassée
        	     billetsPasses = billets.stream()
        	    		 .filter(billet -> billet.getEpreuve().getDate().isBefore(now))
        	    		 .collect(Collectors.toList());
    		 }else {
    			 billetsAVenir = new ArrayList<BilletDto>();
    			 billetsPasses = new ArrayList<BilletDto>();
    		 }

    	     
    	}
    	model.addAttribute("spectateur",  spectateur);
    	model.addAttribute("acces", SecurityUtils.getRoles());
    	model.addAttribute("username", user.getUsername());
    	model.addAttribute("billetsAVenir", billetsAVenir);
    	model.addAttribute("billetsPasses", billetsPasses);
    	model.addAttribute("billetsAValider", billetsAValider);
    	return "organisateur/spectateurs";
    }
    
    
    @PostMapping("/spectateurs/edit")
    public String update(SpectateurDto spectateurDto)
    {
    	spectateurService.updateSpectateur(spectateurDto);
        return "redirect:/organisateur/spectateurs";
    }
 
    
    
    @GetMapping("/spectateurs/findOne")
    @ResponseBody
    public Object[] findOne(Long id) {
        Object[] objects = new Object[1];
        SpectateurDto spectateurDto = spectateurService.findSpectateurById(id);
        objects[0]=spectateurDto;
        return objects;
    }
    

    @GetMapping("/spectateurs/getOne")
    @ResponseBody
    public SpectateurDto findById(Long id)
    {
        return spectateurService.findSpectateurById(id);
    }
    
    @GetMapping("/spectateurs/details")
    public String detailscategories(Model model, Long id){
        SpectateurDto spectateurDto = spectateurService.findSpectateurById(id);
        model.addAttribute("spectateur",spectateurDto);
        model.addAttribute("acces", SecurityUtils.getRoles());
        model.addAttribute("username", spectateurDto.getEmail());
        return "organisateur/details_spectateur";
    }
}
