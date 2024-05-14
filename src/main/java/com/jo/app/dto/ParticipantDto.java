package com.jo.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jo.app.entity.Delegation;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {

	private Long id;
    
    private String nom;
    
    private String prenom;
    
    @NotEmpty(message = "l'email est obligatoire")
    @Email
    private String email;
    
    @JsonIgnoreProperties("participants")
    private Delegation delegation;

    private Long idDelegation;
    
    @JsonIgnoreProperties("participantDto")
	 private List<ResultatDto> resultats;
}
