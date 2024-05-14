package com.jo.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	

    private String prenom;
    
    @NotEmpty(message = "L'email est obligatoire")
    @Email
    private String email;
    
    @JsonIgnoreProperties("spectateurDto")
    private List<BilletDto> billets;
}
