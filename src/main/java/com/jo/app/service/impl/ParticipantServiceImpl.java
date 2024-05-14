package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.jo.app.dto.ParticipantDto;
import com.jo.app.entity.Participant;
import com.jo.app.mapper.ParticipantMapper;
import com.jo.app.repository.ParticipantRepository;
import com.jo.app.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ParticipantServiceImpl implements ParticipantService{

	private ParticipantRepository participantRepository;

   
	public ParticipantServiceImpl(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	@Override
	public List<ParticipantDto> findAllParticipants() {
		List<Participant> participants = participantRepository.findAll();
        return participants.stream().map(ParticipantMapper::mapToParticipantDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createParticipant(ParticipantDto participantDto) {
		Participant participant = ParticipantMapper.mapToParticipant(participantDto);
		participantRepository.save(participant);
		
	}

	@Override
	public ParticipantDto findParticipantById(Long participantId) {
		Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new EntityNotFoundException("Participant introuvable ==> id: " + participantId));
        return ParticipantMapper.mapToParticipantDto(participant);
	}

	@Override
	public void updateParticipant(ParticipantDto participantDto) {
		Participant participant = ParticipantMapper.mapToParticipant(participantDto);
		participantRepository.save(participant);
		
	}

	@Override
	public void deleteParticipant(Long participantId) {
		participantRepository.deleteById(participantId);
		
	}

	@Override
	public List<ParticipantDto> findAllParticipantsByNom(String nom) {
		List<Participant> participants = participantRepository.findParticipantsByNom(nom);
        return participants.stream()
                .map(ParticipantMapper::mapToParticipantDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<ParticipantDto> findParticipantsByNomAndDelegation(String nom, Long delegationId) {
		List<Participant> participants = participantRepository.findParticipantsByNomAndDelegation(nom, delegationId);
        return participants.stream()
                .map(ParticipantMapper::mapToParticipantDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<ParticipantDto> findParticipantsByNomContaining(String partialNom) {
		List<Participant> participants = participantRepository.findParticipantsByNomContaining(partialNom);
        return participants.stream()
                .map(ParticipantMapper::mapToParticipantDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<ParticipantDto> findAllParticipantsOrderedByName() {
		 List<Participant> participants = participantRepository.findAllByOrderByNom();
	        return participants.stream()
	                .map(ParticipantMapper::mapToParticipantDto)
	                .collect(Collectors.toList());
	}

	@Override
	public long countParticipantsByDelegationId(Long delegationId) {
		return participantRepository.countByDelegationId(delegationId);
	}

	@Override
	public boolean existsParticipantByNom(String nom) {
		return participantRepository.existsByNom(nom);
	}

	@Override
	public boolean existsParticipantByNomAndDelegationId(String nom, Long delegationId) {
		return participantRepository.existsByNomAndDelegationId(nom, delegationId);
	}

}
