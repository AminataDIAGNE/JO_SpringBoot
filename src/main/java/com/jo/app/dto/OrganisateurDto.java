package com.jo.app.dto;


import com.jo.app.util.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganisateurDto {
	
	private Long id;
    
    private String nom;
    
    private String prenom;
    
    @NotEmpty(message = "Email est obligatoire")
    @Email
    private String email;
    
    private Role role;
}
