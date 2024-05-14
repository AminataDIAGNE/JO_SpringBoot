package com.jo.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jo.app.dto.BilletDto;
import com.jo.app.dto.EpreuveDto;
import com.jo.app.dto.ResultatDto;
import com.jo.app.entity.Billet;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Resultat;

public class EpreuveMapper {
	
	/**
	 * Mappe une entité Epreuve vers un objet EpreuveDto.
	 *
	 * @param epreuve Le Epreuve à mapper.
	 * @return Un objet EpreuveDto contenant les données mappées.
	 */
	public static EpreuveDto mapToEpreuveDto(Epreuve epreuve){
		List<Billet> billets = epreuve.getBillets() != null ? epreuve.getBillets() : new ArrayList<Billet>();
		List<Resultat> resultats = epreuve.getResultats() != null ? epreuve.getResultats() : new ArrayList<Resultat>();
        return EpreuveDto.builder()
                .id(epreuve.getId())
                .nom(epreuve.getNom())
                .date(epreuve.getDate())
                .nombrePlacesMisesEnVente(epreuve.getNombrePlacesMisesEnVente())
                .infrastructureSportive(epreuve.getInfrastructureSportive())
                .billets(billets.stream()
                        .map((billet) -> BilletMapper.mapToBilletDto(billet))
                        .collect(Collectors.toList()))
                .resultats(resultats.stream()
                        .map((resultat) -> ResultatMapper.mapToResultatDto(resultat))
                        .collect(Collectors.toList()))
                .build();
    }
	
	/**
	 * Mappe un objet EpreuveDto vers une entité Epreuve.
	 *
	 * @param epreuveDto Le EpreuveDto à mapper.
	 * @return Une entité Epreuve contenant les données mappées.
	 */
	public static Epreuve mapToEpreuve(EpreuveDto epreuveDto){
		List<BilletDto> billetDtos = epreuveDto.getBillets() != null ? epreuveDto.getBillets() : new ArrayList<BilletDto>();
		List<ResultatDto> resultatDtos = epreuveDto.getResultats() != null ? epreuveDto.getResultats() : new ArrayList<ResultatDto>();
        return Epreuve.builder()
        		.id(epreuveDto.getId())
        		.id(epreuveDto.getId())
                .nom(epreuveDto.getNom())
                .date(epreuveDto.getDate())
                .nombrePlacesMisesEnVente(epreuveDto.getNombrePlacesMisesEnVente())
                .infrastructureSportive(epreuveDto.getInfrastructureSportive())
                .billets(billetDtos.stream()
                        .map((billet) -> BilletMapper.mapToBillet(billet))
                        .collect(Collectors.toList()))
                .resultats(resultatDtos.stream()
                        .map((resultat) -> ResultatMapper.mapToResultat(resultat))
                        .collect(Collectors.toList()))
                .build();
    }

}
