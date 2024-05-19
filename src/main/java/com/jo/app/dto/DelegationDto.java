package com.jo.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DelegationDto {

	 private Long id;
	
	 @NotEmpty(message = "Le nom de la délégation est obligatoire")
	 private String nom;
		
	 private int nombreMedaillesOr;
	    
	 private int nombreMedaillesArgent;
	    
	 private int nombreMedaillesBronze;

	@JsonIgnore
	 @JsonIgnoreProperties("delegationDto")
	 private List<ParticipantDto> participants;
}
