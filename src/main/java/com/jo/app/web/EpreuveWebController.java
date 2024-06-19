package com.jo.app.web;

import com.jo.app.dto.EpreuveDto;
import com.jo.app.entity.InfrastructureSportive;
import com.jo.app.mapper.InfrastructureSportiveMapper;
import com.jo.app.service.EpreuveService;
import com.jo.app.service.InfrastructureSportiveService;
import com.jo.app.util.SecurityUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
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
        User user = SecurityUtils.getCurrentUser();
        List<String> acces = SecurityUtils.getRoles();
        if(acces.contains("ROLE_PARTICIPANT") || acces.contains("ROLE_CONTROLEUR") || acces.contains("ROLE_SPECTATEUR")) {
            LocalDateTime now = LocalDateTime.now();
            List<EpreuveDto> epreuvesAParticiper = epreuveService.findAllEpreuves().stream()
                    .filter(epreuve -> epreuve.getDate().isAfter(now))
                    .collect(Collectors.toList());
            model.addAttribute("epreuves", epreuvesAParticiper);

        }else{
            model.addAttribute("epreuves", epreuveService.findAllEpreuves());

        }


        model.addAttribute("infraSportives", infrastructureSportiveService.findAllInfrastructureSportives());
        model.addAttribute("acces", SecurityUtils.getRoles());
        model.addAttribute("username", user.getUsername());
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
        User user = SecurityUtils.getCurrentUser();
        EpreuveDto epreuveDto = epreuveService.findEpreuveById(id);
        model.addAttribute("epreuve",epreuveDto);
        model.addAttribute("billets", epreuveDto.getBillets());
        model.addAttribute("resultats", epreuveDto.getResultats());
        model.addAttribute("acces", SecurityUtils.getRoles());
        model.addAttribute("username", user.getUsername());
        return "organisateur/details_epreuve";
    }

    @GetMapping("/epreuves/error")
    public String redirectError(Model model) {
        User user = SecurityUtils.getCurrentUser();
        model.addAttribute("acces", SecurityUtils.getRoles());
        model.addAttribute("username", user.getUsername());
        return "error";
    }

}