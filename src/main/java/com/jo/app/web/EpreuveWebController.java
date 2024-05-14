package com.jo.app.web;

import com.jo.app.dto.EpreuveDto;
import com.jo.app.entity.InfrastructureSportive;
import com.jo.app.mapper.InfrastructureSportiveMapper;
import com.jo.app.service.EpreuveService;
import com.jo.app.service.InfrastructureSportiveService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisateur")
public class EpreuveWebController {

    private final EpreuveService epreuveService;
    private final InfrastructureSportiveService infrastructureSportiveService;

    public EpreuveWebController(EpreuveService epreuveService, InfrastructureSportiveService infrastructureSportiveService) {
        this.epreuveService = epreuveService;
        this.infrastructureSportiveService = infrastructureSportiveService;
    }

    @GetMapping("/epreuves")
    public String AllEpreuves(Model model) {
    	model.addAttribute("epreuves", epreuveService.findAllEpreuves());
    	model.addAttribute("infraSportives", infrastructureSportiveService.findAllInfrastructureSportives());
    	return "organisateur/epreuve";
    }

    @PostMapping("/epreuves/new")
    public String save(EpreuveDto epreuveDto)
    {
    	InfrastructureSportive infra = InfrastructureSportiveMapper.mapToInfrastructureSportive(infrastructureSportiveService.findInfrastructureSportiveById(epreuveDto.getIdInfrasSportive()));
    	epreuveDto.setInfrastructureSportive(infra);
    	epreuveService.createEpreuve(epreuveDto);
        return "redirect:/organisateur/epreuves";
    }
    
    @PostMapping("/epreuves/edit")
    public String update(EpreuveDto epreuveDto)
    {
    	InfrastructureSportive infra = InfrastructureSportiveMapper.mapToInfrastructureSportive(infrastructureSportiveService.findInfrastructureSportiveById(epreuveDto.getIdInfrasSportive()));
    	epreuveDto.setInfrastructureSportive(infra);
    	epreuveService.updateEpreuve(epreuveDto);
        return "redirect:/organisateur/epreuves";
    }
    
    @GetMapping(value="/epreuves/delete")
    public String delete(Long id)
    {
    	epreuveService.deleteEpreuve(id);
        return "redirect:/organisateur/epreuves";
    }
    
    @GetMapping("/epreuves/findOne")
    @ResponseBody
    public Object[] findOne(Long id) {
        Object[] objects = new Object[2];
        EpreuveDto epreuveDto = epreuveService.findEpreuveById(id);
        objects[0]=epreuveDto;
        objects[1] = epreuveDto.getInfrastructureSportive();
        return objects;
    }
    

    @GetMapping("/epreuves/getOne")
    @ResponseBody
    public EpreuveDto findById(Long id)
    {
        return epreuveService.findEpreuveById(id);
    }
    
    @GetMapping("/epreuves/details")
    public String detailscategories(Model model, Long id){
        EpreuveDto epreuveDto = epreuveService.findEpreuveById(id);
        model.addAttribute("epreuve",epreuveDto);
        model.addAttribute("billets", epreuveDto.getBillets());
        model.addAttribute("resultats", epreuveDto.getResultats());
        return "organisateur/details_epreuve";
    }
}
