package com.jo.app.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jo.app.entity.Participant;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jo.app.entity.InfrastructureSportive;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EpreuveDto {

	private Long id;
    
    private String nom;
    
    @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm a")
    private LocalDateTime date;
    
    private int nombrePlacesMisesEnVente;

    @JsonIgnoreProperties("epreuves")
    private InfrastructureSportive infrastructureSportive;

    @JsonIgnore
    private Long idInfrasSportive; // utiliser pour l'appli web
    
    private double prixUnitaireBillet;
    
    @JsonIgnoreProperties("epreuveDto")
    private List<BilletDto> billets;
    
    @JsonIgnoreProperties("epreuveDto")
    private List<ResultatDto> resultats;

    private int nombreLimiteParticipant;

    private List<Participant> participants;
}
