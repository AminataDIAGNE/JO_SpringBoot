package com.jo.app.web;
import com.jo.app.entity.Role;
import com.jo.app.entity.User;
import com.jo.app.service.RoleService;
import com.jo.app.service.UserService;
import com.jo.app.util.SecurityUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisateur")
public class RoleWebController {

    private final RoleService roleService;
    private final UserService userService;

    public RoleWebController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/roles")
    public String AllRoles(Model model) {
    	org.springframework.security.core.userdetails.User user = SecurityUtils.getCurrentUser();
    	model.addAttribute("roles", roleService.findAllRoles());
    	model.addAttribute("acces", SecurityUtils.getRoles());
    	model.addAttribute("username", user.getUsername());
    	return "organisateur/role";
    }

    @PostMapping("/roles/new")
    public String save(Role role)
    {
    	roleService.saveRole(role);
        return "redirect:/organisateur/roles";
    }
    
    @PostMapping("/roles/edit")
    public String update(Role role)
    {
    	roleService.updateRole(role);
        return "redirect:/organisateur/roles";
    }
    
    @GetMapping("/roles/delete")
    public String delete(Long id)
    {
    	Role role = roleService.findById(id);
        if (role != null) {
            // Supprimer l'association entre les utilisateurs et ce rôle
            for (User user : role.getUsers()) {
                user.getRoles().remove(role);
                userService.save(user);
            }
            // Supprimer le rôle
            roleService.deleteRole(id);
        }
        return "redirect:/organisateur/roles";
    }
    
    @GetMapping("/roles/findOne")
    @ResponseBody
    public Object[] findOne(Long id) {
        Object[] objects = new Object[1];
        Role role = roleService.findById(id);
        objects[0]=role;
        return objects;
    }
    

    @GetMapping("/roles/getOne")
    @ResponseBody
    public Role findById(Long id)
    {
        return roleService.findById(id);
    }
    
}
