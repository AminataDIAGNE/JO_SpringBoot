package com.jo.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.jo.app.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
	
	private Long id;
	
	@NotEmpty(message="Le nom est obligatoire")
	private String nom;
	
	@NotEmpty(message="Le prénom est obligatoire")
	private String prenom;
	
	@NotEmpty(message="Email obligatoire")
	@Email
	private String email;
	
	@NotEmpty(message="Le password est obligatoire")
	private String password;
	
	@NotEmpty(message="Confirmation password obligatoire")
	private String retypePwd;
	
	private List<Role> roles = new ArrayList<Role>();
	
	
}
