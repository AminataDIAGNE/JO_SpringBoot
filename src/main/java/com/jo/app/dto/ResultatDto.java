package com.jo.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	 @JsonIgnoreProperties("resultatDto")
	 private Epreuve epreuve;
	@JsonIgnore
	 @JsonIgnoreProperties("resultatDto")
	 private Participant participant;
	    
	 private int tempsPoints;
	 
	 private int position;

	 private boolean  forfait;
}
