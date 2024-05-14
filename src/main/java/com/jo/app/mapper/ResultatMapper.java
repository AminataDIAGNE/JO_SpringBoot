package com.jo.app.mapper;

import com.jo.app.dto.ResultatDto;
import com.jo.app.entity.Resultat;

public class ResultatMapper {
	
	/**
	 * Mappe une entité Resultat vers un objet ResultatDto.
	 *
	 * @param resultat Le Resultat à mapper.
	 * @return Un objet ResultatDto contenant les données mappées.
	 */
	public static ResultatDto mapToResultatDto(Resultat resultat){
        return ResultatDto.builder()
                .id(resultat.getId())
                .epreuve(resultat.getEpreuve())
                .participant(resultat.getParticipant())
                .tempsPoints(resultat.getTempsPoints())
                .position(resultat.getPosition())
                .build();
    }
	
	/**
	 * Mappe un objet ResultatDto vers une entité Resultat.
	 *
	 * @param resultatDto Le ResultatDto à mapper.
	 * @return Une entité Resultat contenant les données mappées.
	 */
	public static Resultat mapToResultat(ResultatDto resultatDto){
        return Resultat.builder()
        		.id(resultatDto.getId())
                .epreuve(resultatDto.getEpreuve())
                .participant(resultatDto.getParticipant())
                .tempsPoints(resultatDto.getTempsPoints())
                .position(resultatDto.getPosition())
                .build();
    }

}
