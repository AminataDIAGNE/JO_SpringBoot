package com.jo.app.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jo.app.dto.EpreuveDto;
import com.jo.app.service.EpreuveService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.jo.app.dto.ParticipantDto;
import com.jo.app.entity.Participant;
import com.jo.app.entity.Role;
import com.jo.app.entity.User;
import com.jo.app.mapper.ParticipantMapper;
import com.jo.app.repository.ParticipantRepository;
import com.jo.app.repository.RoleRepository;
import com.jo.app.repository.UserRepository;
import com.jo.app.service.ParticipantService;
import com.jo.app.util.PasswordUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ParticipantServiceImpl implements ParticipantService{

	private ParticipantRepository participantRepository;
	private EpreuveService epreuveService;

	//private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private RoleRepository roleRepository;


	public ParticipantServiceImpl(EpreuveService epreuveService ,ParticipantRepository participantRepository, UserRepository userRepository, RoleRepository roleRepository) {
		this.participantRepository = participantRepository;
		this.epreuveService = epreuveService ;
		//this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}


	@Override
	public List<ParticipantDto> findAllParticipants() {
		List<Participant> participants = participantRepository.findAll();
		return participants.stream().map(ParticipantMapper::mapToParticipantDto)
				.collect(Collectors.toList());
	}

	@Override
	public void createParticipant(ParticipantDto participantDto) {

		Participant existingParticipant = participantRepository.findByEmail(participantDto.getEmail());
		if (existingParticipant != null) {
			throw new RuntimeException("participant avec ce mail exist.");
		}
		// Créer un nouvel utilisateur
		User user = new User();
		user.setName(participantDto.getPrenom() +" "+ participantDto.getNom());
		user.setEmail(participantDto.getEmail());
		user.setPassword(PasswordUtil.generatePassword());
		//user.setPassword(passwordEncoder.encode(participantDto.getPassword()));

		// Attribuer le rôle de spectateur
		Role role = roleRepository.findByName("ROLE_PARTICIPANT");
		user.setRoles(Arrays.asList(role));

		// Enregistrer l'utilisateur
		userRepository.save(user);

		// Associer l'utilisateur au participant
		participantDto.setUser(user);

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
		Participant existingParticipant = participantRepository.findByEmail(participantDto.getEmail());
		if (existingParticipant != null) {
			throw new RuntimeException("participant exist.");
		}
		EpreuveDto epreuve = epreuveService.findEpreuveById(participantDto.getDelegation().getId());
		if (epreuve == null) {
			throw new RuntimeException("Epreuve do not exist.");
		}
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
