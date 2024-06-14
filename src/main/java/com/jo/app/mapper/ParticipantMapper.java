package com.jo.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jo.app.dto.ParticipantDto;
import com.jo.app.dto.ResultatDto;
import com.jo.app.entity.Participant;
import com.jo.app.entity.Resultat;

public class ParticipantMapper {
	
	/**
	 * Mappe une entité Participant vers un objet ParticipantDto.
	 *
	 * @param participant Le Participant à mapper.
	 * @return Un objet ParticipantDto contenant les données mappées.
	 */
	public static ParticipantDto mapToParticipantDto(Participant participant){
		List<Resultat> resultats = participant.getResultats() != null ? participant.getResultats() : new ArrayList<Resultat>();
        return ParticipantDto.builder()
                .id(participant.getId())
                .nom(participant.getNom())
                .prenom(participant.getPrenom())
                .email(participant.getEmail())
                .delegation(participant.getDelegation())
                .resultats(resultats.stream()
                        .map((res) -> ResultatMapper.mapToResultatDto(res))
                        .collect(Collectors.toList()))
                .user(participant.getUser())
                .build();
    }
	
	/**
	 * Mappe un objet ParticipantDto vers une entité Participant.
	 *
	 * @param participantDto Le ParticipantDto à mapper.
	 * @return Une entité Participant contenant les données mappées.
	 */
	public static Participant mapToParticipant(ParticipantDto participantDto){
		List<ResultatDto> resultats = participantDto.getResultats() != null ? participantDto.getResultats() : new ArrayList<ResultatDto>();
        return Participant.builder()
        		.id(participantDto.getId())
                .nom(participantDto.getNom())
                .prenom(participantDto.getPrenom())
                .email(participantDto.getEmail())
                .delegation(participantDto.getDelegation())
                .resultats(resultats.stream()
                        .map((res) -> ResultatMapper.mapToResultat(res))
                        .collect(Collectors.toList()))
                .user(participantDto.getUser())
                .build();
    }

}
