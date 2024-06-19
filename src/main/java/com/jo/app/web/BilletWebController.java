package com.jo.app.web;

import com.jo.app.dto.BilletDto;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Spectateur;
import com.jo.app.mapper.EpreuveMapper;
import com.jo.app.mapper.SpectateurMapper;
import com.jo.app.service.BilletService;
import com.jo.app.service.EpreuveService;
import com.jo.app.service.SpectateurService;
import com.jo.app.util.Etat;
import com.jo.app.util.SecurityUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisateur")
public class BilletWebController {

	private final EpreuveService epreuveService;
    private final SpectateurService spectateurService;
    private final BilletService billetService;

    public BilletWebController(BilletService billetService, EpreuveService epreuveService, SpectateurService spectateurService) {
    	this.billetService = billetService;
        this.epreuveService = epreuveService;
        this.spectateurService = spectateurService;
    }

    @GetMapping("/billets")
    public String AllBillets(Model model) {
    	User user = SecurityUtils.getCurrentUser();
    	List<String> acces = SecurityUtils.getRoles();
    	if(acces.contains("ROLE_ORGANISATEUR")) {
    		model.addAttribute("billets", billetService.findAllBillets());
    		model.addAttribute("spectateurs", spectateurService.findAllSpectateurs());
    		
    	}else if(acces.contains("ROLE_SPECTATEUR")) {
    		model.addAttribute("billets", billetService.findAllBySpectateur(spectateurService.findByEmail(user.getUsername())));
    		model.addAttribute("spectateurs", Arrays.asList(spectateurService.findByEmail(user.getUsername())));
    		
    	}else if(acces.contains("ROLE_CONTROLEUR")) {
    		// Obtenir la date actuelle
			 LocalDateTime now = LocalDateTime.now();
    		List<BilletDto> billetsAControler = billetService.findAllBillets().stream()
    	            .filter(billet -> billet.getEpreuve().getDate().isAfter(now) 
    	            		&& (
    	            				Etat.VALIDE.equals(billet.getEtat()) || 
    	            				Etat.INVALIDE.equals(billet.getEtat())
    	            			)
    	            		)
    	            .collect(Collectors.toList());
    		model.addAttribute("billets", billetsAControler);
    	}
    	
    	model.addAttribute("epreuves", epreuveService.findAllEpreuves());
    	model.addAttribute("acces", acces);
    	model.addAttribute("username", user.getUsername());
    	return "organisateur/billet";
    }
    @GetMapping("/billets/paiement")
    public String payer(Model model, Long id) {
    	BilletDto billetDto = billetService.findBilletById(id);
    	billetDto.setEtat(Etat.VALIDE);
    	billetService.updateEtat(billetDto);
    	return "redirect:/organisateur/spectateurs";
    }
    
    @GetMapping("/billets/controler")
    public String controler(Model model, Long id) {
    	BilletDto billetDto = billetService.findBilletById(id);
    	if(Etat.VALIDE.equals(billetDto.getEtat())) {
    		billetDto.setEtat(Etat.INVALIDE);
    	}else {
    		billetDto.setEtat(Etat.VALIDE);
    	}
    
    	billetService.updateEtat(billetDto);
    	return "redirect:/organisateur/billets";
    }
    
    
    @GetMapping("/billets/findByEpreuve")
    public String findAllBilletByEpreuve(Model model, Long idEpreuve) {
    	model.addAttribute("billets", billetService.findAllByEpreuve(epreuveService.findEpreuveById(idEpreuve)));
    	model.addAttribute("roles", SecurityUtils.getRoles());
    	return "organisateur/billets";
    }
    
    @GetMapping("/billets/findBySpectateur")
    public String findAllBilletBySpectateur(Model model, Long idSpectateur) {
    	model.addAttribute("billets", billetService.findAllBySpectateur(spectateurService.findSpectateurById(idSpectateur)));
    	model.addAttribute("roles", SecurityUtils.getRoles());
    	return "organisateur/billets";
    }
    
    @GetMapping("/billets/findByEpreuveAndSpectateur")
    public String findAllBilletBySpectateur(Model model, Long idEpreuve, Long idSpectateur) {
    	model.addAttribute("billets", billetService.findAllByEpreuveAndSpectateur(epreuveService.findEpreuveById(idEpreuve), spectateurService.findSpectateurById(idSpectateur)));
    	model.addAttribute("roles", SecurityUtils.getRoles());
    	return "organisateur/billets";
    }


    @PostMapping("/billets/new")
    public String save(BilletDto billetDTO)
    {
    	try {
    		Epreuve epreuve = EpreuveMapper.mapToEpreuve(epreuveService.findEpreuveById(billetDTO.getIdEpreuve()));
        	Spectateur spectateur = SpectateurMapper.mapToSpectateur(spectateurService.findSpectateurById(billetDTO.getIdSpectateur()));
        	billetDTO.setEpreuve(epreuve);
        	billetDTO.setSpectateur(spectateur);
        	billetService.createBillet(billetDTO);
		} catch (Exception e) {
			e.getMessage();
			return "redirect:/organisateur/billets";
		}
    	
        return "redirect:/organisateur/billets";
    }
    
    @PostMapping("/billets/edit")
    public String update(BilletDto billetDTO)
    {
    	Epreuve epreuve = EpreuveMapper.mapToEpreuve(epreuveService.findEpreuveById(billetDTO.getIdEpreuve()));
    	Spectateur spectateur = SpectateurMapper.mapToSpectateur(spectateurService.findSpectateurById(billetDTO.getIdSpectateur()));
    	billetDTO.setEpreuve(epreuve);
    	billetDTO.setSpectateur(spectateur);
    	billetService.updateBillet(billetDTO);
        return "redirect:/organisateur/billets";
    }
    
    @GetMapping(value="/billets/delete")
    public String delete(Long id)
    {
    	billetService.deleteBillet(id);
        return "redirect:/organisateur/billets";
    }
    
    @GetMapping("/billets/findOne")
    @ResponseBody
    public Object[] findOne(Long id) {
        Object[] objects = new Object[3];
        BilletDto billetDto = billetService.findBilletById(id);
        objects[0]=billetDto;
        objects[1] = billetDto.getEpreuve();
        objects[2] = billetDto.getSpectateur();
        return objects;
    }
    

    @GetMapping("/billets/getOne")
    @ResponseBody
    public BilletDto findById(Long id)
    {
        return billetService.findBilletById(id);
    }
    
    @GetMapping("/billets/details")
    public String detailscategories(Model model, Long id){
    	User user = SecurityUtils.getCurrentUser();
        BilletDto billetDto = billetService.findBilletById(id);
        model.addAttribute("billet",billetDto);
        model.addAttribute("acces", SecurityUtils.getRoles());
        model.addAttribute("username", user.getUsername());
        return "organisateur/details_billet";
    }
}
