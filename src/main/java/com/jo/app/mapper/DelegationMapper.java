package com.jo.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jo.app.dto.DelegationDto;
import com.jo.app.dto.ParticipantDto;
import com.jo.app.entity.Delegation;
import com.jo.app.entity.Participant;

public class DelegationMapper {
	
	/**
	 * Mappe une entité Delegation vers un objet DelegationDto.
	 *
	 * @param delegation Le Delegation à mapper.
	 * @return Un objet DelegationDto contenant les données mappées.
	 */
	public static DelegationDto mapToDelegationDto(Delegation delegation){
		List<Participant> participants = delegation.getParticipants() != null ? delegation.getParticipants() : new ArrayList<Participant>();
        return DelegationDto.builder()
                .id(delegation.getId())
                .nom(delegation.getNom())
                .nombreMedaillesOr(delegation.getNombreMedaillesOr())
                .nombreMedaillesArgent(delegation.getNombreMedaillesArgent())
                .nombreMedaillesBronze(delegation.getNombreMedaillesBronze())
                .participants(participants.stream()
                        .map((participant) -> ParticipantMapper.mapToParticipantDto(participant))
                        .collect(Collectors.toList()))
                .build();
    }
	
	/**
	 * Mappe un objet DelegationDto vers une entité Delegation.
	 *
	 * @param delegationDto Le DelegationDto à mapper.
	 * @return Une entité Delegation contenant les données mappées.
	 */
	public static Delegation mapToDelegation(DelegationDto delegationDto){
		List<ParticipantDto> participantDtos = delegationDto.getParticipants() != null ? delegationDto.getParticipants() : new ArrayList<ParticipantDto>();
        return Delegation.builder()
        		.id(delegationDto.getId())
                .nom(delegationDto.getNom())
                .nombreMedaillesOr(delegationDto.getNombreMedaillesOr())
                .nombreMedaillesArgent(delegationDto.getNombreMedaillesArgent())
                .nombreMedaillesBronze(delegationDto.getNombreMedaillesBronze())
                .participants(participantDtos.stream()
                        .map((participant) -> ParticipantMapper.mapToParticipant(participant))
                        .collect(Collectors.toList()))
                .build();
    }

}
