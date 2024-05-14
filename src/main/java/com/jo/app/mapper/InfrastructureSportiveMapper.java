package com.jo.app.mapper;

import java.util.stream.Collectors;

import com.jo.app.dto.InfrastructureSportiveDto;
import com.jo.app.entity.InfrastructureSportive;

public class InfrastructureSportiveMapper {

	/**
	 * Mappe une entité InfrastructureSportive vers un objet InfrastructureSportiveDto.
	 *
	 * @param infrastructureSportive Le InfrastructureSportive à mapper.
	 * @return Un objet InfrastructureSportiveDto contenant les données mappées.
	 */
	public static InfrastructureSportiveDto mapToInfrastructureSportiveDto(InfrastructureSportive infrastructureSportive){
        return InfrastructureSportiveDto.builder()
                .id(infrastructureSportive.getId())
                .nom(infrastructureSportive.getNom())
                .adresse(infrastructureSportive.getAdresse())
                .capacite(infrastructureSportive.getCapacite())
                .epreuves(infrastructureSportive.getEpreuves().stream()
                		.map((epreuve) -> EpreuveMapper.mapToEpreuveDto(epreuve))
                        .collect(Collectors.toList()))
                .build();
    }
	
	/**
	 * Mappe un objet InfrastructureSportiveDto vers une entité InfrastructureSportive.
	 *
	 * @param infrastructureSportiveDto Le InfrastructureSportiveDto à mapper.
	 * @return Une entité InfrastructureSportive contenant les données mappées.
	 */
	public static InfrastructureSportive mapToInfrastructureSportive(InfrastructureSportiveDto infrastructureSportiveDto){
        return InfrastructureSportive.builder()
        		.id(infrastructureSportiveDto.getId())
        		.nom(infrastructureSportiveDto.getNom())
                .adresse(infrastructureSportiveDto.getAdresse())
                .capacite(infrastructureSportiveDto.getCapacite())
                .epreuves(infrastructureSportiveDto.getEpreuves().stream()
                		.map((epreuve) -> EpreuveMapper.mapToEpreuve(epreuve))
                        .collect(Collectors.toList()))
                .build();
    }
}
