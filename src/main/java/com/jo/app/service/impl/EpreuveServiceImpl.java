package com.jo.app.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.jo.app.entity.Participant;
import com.jo.app.entity.Resultat;
import com.jo.app.repository.ParticipantRepository;
import com.jo.app.repository.ResultatRepository;
import com.jo.app.service.InfrastructureSportiveService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.jo.app.dto.EpreuveDto;
import com.jo.app.entity.Epreuve;
import com.jo.app.mapper.EpreuveMapper;
import com.jo.app.repository.EpreuveRepository;
import com.jo.app.service.EpreuveService;

@Service
public class EpreuveServiceImpl implements EpreuveService{

	private EpreuveRepository epreuveRepository;
	private InfrastructureSportiveService infrastructureSportiveService;

	private ParticipantRepository participantRepository;

	private ResultatRepository resultatRepository;
	public EpreuveServiceImpl(EpreuveRepository epreuveRepository, InfrastructureSportiveService infrastructureSportiveService ,ParticipantRepository participantRepository ,ResultatRepository resultatRepository) {
		this.epreuveRepository = epreuveRepository;
		this.participantRepository = participantRepository;
		this.infrastructureSportiveService = infrastructureSportiveService;
		this.resultatRepository = resultatRepository;
	}

	@Override
	public List<EpreuveDto> findAllEpreuves() {
		List<Epreuve> epreuves = epreuveRepository.findAll();
        return epreuves.stream().map(EpreuveMapper::mapToEpreuveDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createEpreuve(EpreuveDto epreuveDto) {
		// check if infrastructure existe
		if (infrastructureSportiveService.findInfrastructureSportiveById(epreuveDto.getInfrastructureSportive().getId()) != null) {
			Epreuve epreuve = EpreuveMapper.mapToEpreuve(epreuveDto);
			epreuveRepository.save(epreuve);
		} else {
			throw new RuntimeException("L'infrastructure sportive spécifiée n'existe pas.");
		}
	}

	@Override
	public EpreuveDto findEpreuveById(Long epreuveId) {
		Epreuve epreuve = epreuveRepository.findById(epreuveId).get();
		if (epreuve == null) {
			throw new IllegalStateException("L'Epreuve n'existe pas");
		}
		return EpreuveMapper.mapToEpreuveDto(epreuve);
	}

	@Override
	public void updateEpreuve(EpreuveDto epreuveDto) {
		if (infrastructureSportiveService.findInfrastructureSportiveById(epreuveDto.getInfrastructureSportive().getId()) != null) {
			Epreuve epreuve = EpreuveMapper.mapToEpreuve(epreuveDto);
			epreuveRepository.save(epreuve);
		} else {
			throw new RuntimeException("L'infrastructure sportive spécifiée n'existe pas.");
		}
	}

	@Override
	public void deleteEpreuve(Long epreuveId) {
		epreuveRepository.deleteById(epreuveId);
		
	}

	@Transactional
	public EpreuveDto inscrireParticipant(Long epreuveId, Long participantId) {
		Epreuve epreuve = epreuveRepository.findById(epreuveId)
				.orElseThrow(() -> new IllegalArgumentException("Epreuve non trouvée"));
		Participant participant = participantRepository.findById(participantId)
				.orElseThrow(() -> new IllegalArgumentException("Participant non trouvé"));

		LocalDateTime currentDate = LocalDateTime.now();
		if ((participant.getEpreuves().contains(epreuve))) {
			throw new IllegalStateException("participant déjà inscrite dans la liste de l' epreuve");
		}
		if (ChronoUnit.DAYS.between(currentDate, epreuve.getDate()) <= 10) {
			throw new IllegalStateException("Les inscriptions sont clôturées pour cette épreuve.");
		}
		if (epreuve.getParticipants().size() >= epreuve.getNombreLimiteParticipant()) {
			throw new IllegalStateException("Nombre de places maximum atteint pour cette épreuve.");
		}

		epreuve.getParticipants().add(participant);
		participant.getEpreuves().add(epreuve);

		epreuveRepository.save(epreuve);
		participantRepository.save(participant);

		return EpreuveMapper.mapToEpreuveDto(epreuve);
	}

	@Transactional
	public void desinscrireParticipant(Long epreuveId, Long participantId) {
		Epreuve epreuve = epreuveRepository.findById(epreuveId)
				.orElseThrow(() -> new IllegalArgumentException("Epreuve non trouvée"));
		Participant participant = participantRepository.findById(participantId)
				.orElseThrow(() -> new IllegalArgumentException("Participant non trouvé"));

		LocalDateTime currentDate = LocalDateTime.now();
		boolean withinTenDays = ChronoUnit.DAYS.between(currentDate, epreuve.getDate()) <= 10;

		if (withinTenDays) {
			// Marquer les résultats comme "forfait"
			List<Resultat> resultats = resultatRepository.findByEpreuveAndParticipant(epreuve, participant);
			for (Resultat resultat : resultats) {
				resultat.setForfait(true);
				resultatRepository.save(resultat);
			}
		} else {
			epreuve.getParticipants().remove(participant);
			participant.getEpreuves().remove(epreuve);

			epreuveRepository.save(epreuve);
			participantRepository.save(participant);
		}
	}

}
