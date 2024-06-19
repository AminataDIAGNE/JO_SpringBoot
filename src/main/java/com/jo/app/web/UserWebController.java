package com.jo.app.web;

import com.jo.app.dto.RegistrationDto;
import com.jo.app.entity.User;
import com.jo.app.service.RoleService;
import com.jo.app.service.UserService;
import com.jo.app.util.SecurityUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisateur")
public class UserWebController {

    private final UserService userService;
    private final RoleService roleService; 

    public UserWebController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    
    @GetMapping("/users")
    public String findAll(Model model) {
    	org.springframework.security.core.userdetails.User user = SecurityUtils.getCurrentUser();
    	model.addAttribute("users",  userService.findAllUsers());
    	model.addAttribute("roles",  roleService.findAllRoles());
    	model.addAttribute("acces", SecurityUtils.getRoles());
    	model.addAttribute("username", user.getUsername());
    	return "organisateur/user";
    }

    
    @PostMapping("/users/new")
    public String save(RegistrationDto user)
    {
    	
    	userService.saveUser(user);
        return "redirect:/organisateur/users";
    }
    
    @PostMapping("/users/edit")
    public String update(RegistrationDto user)
    {
    	userService.saveUser(user);
        return "redirect:/organisateur/users";
    }
    
    @GetMapping(value="/users/delete")
    public String delete(Long id)
    {
    	
    	userService.deleteUser(id);
    	return "redirect:/organisateur/users";
    }
    
    
    @GetMapping("/users/findOne")
    @ResponseBody
    public Object[] findOne(Long id) {
        Object[] objects = new Object[1];
        User user = userService.findById(id);
        objects[0]=user;
        return objects;
    }
    

    @GetMapping("/users/getOne")
    @ResponseBody
    public User findById(Long id)
    {
        return userService.findById(id);
    }
 
}
