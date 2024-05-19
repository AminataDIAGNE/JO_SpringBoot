package com.jo.app.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Spectateur;
import com.jo.app.util.Etat;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BilletDto {

	private Long id;
   
	@JsonIgnoreProperties("billets")
    private Epreuve epreuve;
    
	@JsonIgnoreProperties("billets")
    private Spectateur spectateur;
    
    private double prixTotal;
    
    private int quantite;
    
    private Etat etat;
    // Champs supplémentaires pour les détails du remboursement
    private double montantRemboursement;
    private String messageConfirmation;
}
