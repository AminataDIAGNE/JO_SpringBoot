package com.jo.app.web;

import com.jo.app.dto.SpectateurDto;
import com.jo.app.service.SpectateurService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisateur")
public class SpectateurWebController {

    private final SpectateurService spectateurService;

    public SpectateurWebController(SpectateurService spectateurService) {
        this.spectateurService = spectateurService;
    }
    
    @GetMapping("/spectateurs")
    public String allSpectateurs(Model model) {
    	model.addAttribute("spectateurs",  spectateurService.findAllSpectateurs());
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
        return "organisateur/details_spectateur";
    }
}
