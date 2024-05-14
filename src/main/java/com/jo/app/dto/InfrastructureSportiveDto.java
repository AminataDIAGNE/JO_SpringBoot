package com.jo.app.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfrastructureSportiveDto {

	private Long id;
    
    private String nom;
    
    private String adresse;
    
    private int capacite;
    
    @JsonIgnoreProperties("infrastructureSportiveDto")
    private List<EpreuveDto> epreuves;
}
