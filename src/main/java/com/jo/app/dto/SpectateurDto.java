package com.jo.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jo.app.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpectateurDto {
	
    private Long id;
    
    @NotEmpty(message = "Le nom du spectateur est obligatoire")
    private String nom;
	
    @NotEmpty(message = "Le prénom est obligatoire")

    private String prenom;
    
    @NotEmpty(message = "Email obligatoire")
    @Email
    private String email;

    
    @JsonIgnoreProperties("spectateurDto")
    private List<BilletDto> billets;
    
    // pour lier un participant à un utilisateur avec un/des role(s)
    @JsonIgnore
    private User user;
    
    @JsonIgnore
    @NotEmpty(message = "Le password est obligatoire")
    private String password;
    
    @JsonIgnore
    @NotEmpty(message = "La confirmation du password est obligatoire")
    private String confirmPassword;
}
