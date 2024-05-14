package com.jo.app.mapper;

import java.util.stream.Collectors;

import com.jo.app.dto.SpectateurDto;
import com.jo.app.entity.Spectateur;

public class SpectateurMapper {
	
	/**
	 * Mappe une entité Spectateur vers un objet SpectateurDto.
	 *
	 * @param spectateur Le Spectateur à mapper.
	 * @return Un objet SpectateurDto contenant les données mappées.
	 */
	public static SpectateurDto mapToSpectateurDto(Spectateur spectateur){
        return SpectateurDto.builder()
                .id(spectateur.getId())
                .nom(spectateur.getNom())
                .prenom(spectateur.getPrenom())
                .email(spectateur.getEmail())
                .billets(spectateur.getBillets().stream()
                        .map((billet) -> BilletMapper.mapToBilletDto(billet))
                        .collect(Collectors.toList()))
                .build();
    }
	
	/**
	 * Mappe un objet SpectateurDto vers une entité Spectateur.
	 *
	 * @param spectateurDto Le SpectateurDto à mapper.
	 * @return Une entité Spectateur contenant les données mappées.
	 */
	public static Spectateur mapToSpectateur(SpectateurDto spectateurDto){
        return Spectateur.builder()
        		.id(spectateurDto.getId())
                .nom(spectateurDto.getNom())
                .prenom(spectateurDto.getPrenom())
                .email(spectateurDto.getEmail())
                .billets(spectateurDto.getBillets().stream()
                        .map((billet) -> BilletMapper.mapToBillet(billet))
                        .collect(Collectors.toList()))
                .build();
    }

}
