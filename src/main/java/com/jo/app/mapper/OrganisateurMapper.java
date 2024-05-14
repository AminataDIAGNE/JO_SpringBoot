package com.jo.app.mapper;

import com.jo.app.dto.OrganisateurDto;
import com.jo.app.entity.Organisateur;

public class OrganisateurMapper {
	
	/**
	 * Mappe une entité Organisateur vers un objet OrganisateurDto.
	 *
	 * @param organisateur Le Organisateur à mapper.
	 * @return Un objet OrganisateurDto contenant les données mappées.
	 */
	public static OrganisateurDto mapToOrganisateurDto(Organisateur organisateur){
        return OrganisateurDto.builder()
                .id(organisateur.getId())
                .email(organisateur.getEmail())
                .nom(organisateur.getNom())
                .prenom(organisateur.getPrenom())
                .role(organisateur.getRole())
                .build();
    }
	 
	/**
	 * Mappe un objet OrganisateurDto vers une entité Organisateur.
	 *
	 * @param organisateurDto Le OrganisateurDto à mapper.
	 * @return Une entité Organisateur contenant les données mappées.
	 */
	public static Organisateur mapToOrganisateur(OrganisateurDto organisateurDto){
        return Organisateur.builder()
        		.id(organisateurDto.getId())
        		.email(organisateurDto.getEmail())
                .nom(organisateurDto.getNom())
                .prenom(organisateurDto.getPrenom())
                .role(organisateurDto.getRole())
                .build();
    }

}
