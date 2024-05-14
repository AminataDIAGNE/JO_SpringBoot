package com.jo.app.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Spectateur;
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
    
    private double prix;
    
    private String etat;
}
