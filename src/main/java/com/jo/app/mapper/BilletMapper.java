package com.jo.app.mapper;

import com.jo.app.dto.BilletDto;
import com.jo.app.entity.Billet;

public class BilletMapper {
	
	/**
	 * Mappe une entité Billet vers un objet BilletDto.
	 *
	 * @param billet Le Billet à mapper.
	 * @return Un objet BilletDto contenant les données mappées.
	 */
	public static BilletDto mapToBilletDto(Billet billet){
        return BilletDto.builder()
                .id(billet.getId())
                .prix(billet.getPrix())
                .etat(billet.getEtat())
                .epreuve(billet.getEpreuve())
                .spectateur(billet.getSpectateur())
                .build();
    }
	
	/**
	 * Mappe un objet BilletDto vers une entité Billet.
	 *
	 * @param billetDto Le BilletDto à mapper.
	 * @return Une entité Billet contenant les données mappées.
	 */
	public static Billet mapToBillet(BilletDto billetDto){
        return Billet.builder()
        		.id(billetDto.getId())
                .prix(billetDto.getPrix())
                .etat(billetDto.getEtat())
                .epreuve(billetDto.getEpreuve())
                .spectateur(billetDto.getSpectateur())
                .build();
    }

}
