package com.jo.app.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityUtils {

    public static User getCurrentUser(){
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof User){
            return (User) principle;
        }
        return null;
    }

    public static List<String> getRoles(){
    	User user = getCurrentUser();
    	
    	  // Ajouter les rôles de l'utilisateur actuel au modèle
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();;
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return roles;
    }
}
