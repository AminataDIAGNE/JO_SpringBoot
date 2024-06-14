package com.jo.app.web;

import com.jo.app.dto.BilletDto;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Spectateur;
import com.jo.app.mapper.EpreuveMapper;
import com.jo.app.mapper.SpectateurMapper;
import com.jo.app.service.BilletService;
import com.jo.app.service.EpreuveService;
import com.jo.app.service.SpectateurService;

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
    	model.addAttribute("epreuves", epreuveService.findAllEpreuves());
    	model.addAttribute("spectateurs", spectateurService.findAllSpectateurs());
    	model.addAttribute("billets", billetService.findAllBillets());
    	return "organisateur/billet";
    }
    
    @GetMapping("/billets/findByEpreuve")
    public String findAllBilletByEpreuve(Model model, Long idEpreuve) {
    	model.addAttribute("billets", billetService.findAllByEpreuve(epreuveService.findEpreuveById(idEpreuve)));
    	return "organisateur/billet";
    }
    
    @GetMapping("/billets/findBySpectateur")
    public String findAllBilletBySpectateur(Model model, Long idSpectateur) {
    	model.addAttribute("billets", billetService.findAllBySpectateur(spectateurService.findSpectateurById(idSpectateur)));
    	return "organisateur/billet";
    }
    
    @GetMapping("/billets/findByEpreuveAndSpectateur")
    public String findAllBilletBySpectateur(Model model, Long idEpreuve, Long idSpectateur) {
    	model.addAttribute("billets", billetService.findAllByEpreuveAndSpectateur(epreuveService.findEpreuveById(idEpreuve), spectateurService.findSpectateurById(idSpectateur)));
    	return "organisateur/billet";
    }


    @PostMapping("/billets/new")
    public String save(BilletDto billetDTO)
    {
    	Epreuve epreuve = EpreuveMapper.mapToEpreuve(epreuveService.findEpreuveById(billetDTO.getIdEpreuve()));
    	Spectateur spectateur = SpectateurMapper.mapToSpectateur(spectateurService.findSpectateurById(billetDTO.getIdSpectateur()));
    	billetDTO.setEpreuve(epreuve);
    	billetDTO.setSpectateur(spectateur);
    	billetService.createBillet(billetDTO);
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
        BilletDto billetDto = billetService.findBilletById(id);
        model.addAttribute("billet",billetDto);
        return "organisateur/details_billet";
    }
}
