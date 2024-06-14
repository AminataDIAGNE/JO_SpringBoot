package com.jo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompteBancaireDto {

	private String numeroCompte;
	
	private String validite;
	
	private String titulaire;
	
	private String authorizedSignature;
	
}
