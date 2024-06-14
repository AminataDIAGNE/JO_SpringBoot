package com.jo.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jo.app.dto.BilletDto;
import com.jo.app.dto.SpectateurDto;
import com.jo.app.entity.Billet;
import com.jo.app.entity.Spectateur;

public class SpectateurMapper {
	
	/**
	 * Mappe une entité Spectateur vers un objet SpectateurDto.
	 *
	 * @param spectateur Le Spectateur à mapper.
	 * @return Un objet SpectateurDto contenant les données mappées.
	 */
	public static SpectateurDto mapToSpectateurDto(Spectateur spectateur){
		List<Billet> billets = spectateur.getBillets() != null ? spectateur.getBillets() : new ArrayList<Billet>();
        return SpectateurDto.builder()
                .id(spectateur.getId())
                .nom(spectateur.getNom())
                .prenom(spectateur.getPrenom())
                .email(spectateur.getEmail())
                .billets(billets.stream()
                        .map((billet) -> BilletMapper.mapToBilletDto(billet))
                        .collect(Collectors.toList()))
                .user(spectateur.getUser())
                .build();
    }
	
	/**
	 * Mappe un objet SpectateurDto vers une entité Spectateur.
	 *
	 * @param spectateurDto Le SpectateurDto à mapper.
	 * @return Une entité Spectateur contenant les données mappées.
	 */
	public static Spectateur mapToSpectateur(SpectateurDto spectateurDto){
		List<BilletDto> billetDtos = spectateurDto.getBillets() != null ? spectateurDto.getBillets() : new ArrayList<BilletDto>();
        return Spectateur.builder()
        		.id(spectateurDto.getId())
                .nom(spectateurDto.getNom())
                .prenom(spectateurDto.getPrenom())
                .email(spectateurDto.getEmail())
                .billets(billetDtos.stream()
                        .map((billet) -> BilletMapper.mapToBillet(billet))
                        .collect(Collectors.toList()))
                .user(spectateurDto.getUser())
                .build();
    }

}
