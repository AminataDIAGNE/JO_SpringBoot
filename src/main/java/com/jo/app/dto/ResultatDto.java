package com.jo.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Participant;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultatDto {

	 private Long id;
	 
	 @JsonIgnoreProperties("resultats")
	 private Epreuve epreuve;
	   
	 @JsonIgnoreProperties("resultats")
	 private Participant participant;
	    
	 private int tempsPoints;
	 
	 private int position;
}
