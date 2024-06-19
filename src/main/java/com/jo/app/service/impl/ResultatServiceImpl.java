package com.jo.app.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jo.app.dto.DelegationDto;
import com.jo.app.dto.EpreuveDto;
import com.jo.app.dto.ParticipantDto;
import com.jo.app.entity.Delegation;
import com.jo.app.repository.DelegationRepository;
import com.jo.app.service.EpreuveService;
import com.jo.app.service.ParticipantService;
import org.springframework.stereotype.Service;

import com.jo.app.dto.ResultatDto;
import com.jo.app.entity.Resultat;
import com.jo.app.mapper.ResultatMapper;
import com.jo.app.repository.ResultatRepository;
import com.jo.app.service.ResultatService;

@Service
public class ResultatServiceImpl implements ResultatService {

	private ResultatRepository resultatRepository;
	private EpreuveService epreuveService;

	private DelegationRepository delegationRepository;
	private ParticipantService participantService;

	public ResultatServiceImpl(DelegationRepository delegationRepository, ResultatRepository resultatRepository, EpreuveService epreuveService, ParticipantService participantService) {
		this.resultatRepository = resultatRepository;
		this.epreuveService = epreuveService;
		this.participantService = participantService;
		this.delegationRepository = delegationRepository;
	}
	@Override
	public List<ResultatDto> findAllResultats() {
		List<Resultat> resultats = resultatRepository.findAll();
        return resultats.stream().map(ResultatMapper::mapToResultatDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createResultat(ResultatDto resultatDto) {
		// Vérifier si l'épreuve existe
		EpreuveDto epreuve = epreuveService.findEpreuveById(resultatDto.getEpreuve().getId());
		if (epreuve == null) {
			throw new RuntimeException("Epreuve do not exist.");
		}

		// Vérifier si le participant existe
		ParticipantDto participant = participantService.findParticipantById(resultatDto.getParticipant().getId());
		if (participant == null) {
			throw new RuntimeException("Participant do not exist");
		}

		Resultat resultat = ResultatMapper.mapToResultat(resultatDto);
		resultatRepository.save(resultat);
	}


	@Override
	public ResultatDto findResultatById(Long resultatId) {
		Resultat resultat = resultatRepository.findById(resultatId).get();
		return ResultatMapper.mapToResultatDto(resultat);
	}

	@Override
	public void updateResultat(ResultatDto resultatDto) {
		// Vérifier si le résultat existe
		Resultat existingResultat = resultatRepository.findById(resultatDto.getId()).orElse(null);
		if (existingResultat == null) {
			throw new RuntimeException("Resultat do not exist.");
		}

		// Vérifier si l'épreuve existe
		EpreuveDto epreuve = epreuveService.findEpreuveById(resultatDto.getEpreuve().getId());
		if (epreuve == null) {
			throw new RuntimeException("Epreuve do not exist");
		}

		// Vérifier si le participant existe
		ParticipantDto participant = participantService.findParticipantById(resultatDto.getParticipant().getId());
		if (participant == null) {
			throw new RuntimeException("Participant do not exist");
		}

		Resultat resultat = ResultatMapper.mapToResultat(resultatDto);
		resultatRepository.save(resultat);
	}

	@Override
	public void deleteResultat(Long resultatId) {
		resultatRepository.deleteById(resultatId);

	}

	@Override
	public List<ResultatDto> findResultatsByParticipant(Long idParticipant) {
		// Récupérer les résultats associés au participant en utilisant son ID
	    List<Resultat> resultats = resultatRepository.findResultatsByparticipantId(idParticipant);

	    // Mapper les résultats en ResultatDto et return
	    return resultats.stream()
	            .map(ResultatMapper::mapToResultatDto)
	            .collect(Collectors.toList());

	}

	@Override
	public List<DelegationDto> calculerClassementGeneral() {
		List<Resultat> resultats = resultatRepository.findAll();

		// Calcul du classement général par délégation
		Map<Long, List<Resultat>> groupedByDelegation = resultats.stream()
				.filter(resultat -> !resultat.isForfait()) // Filtrer les résultats sans statut "forfait"
				.collect(Collectors.groupingBy(resultat -> resultat.getParticipant().getDelegation().getId()));

		List<DelegationDto> classementGeneral = groupedByDelegation.entrySet().stream()
				.map(entry -> {
					Long delegationId = entry.getKey();
					List<Resultat> delegationResults = entry.getValue();

					// Calcul des médailles pour la délégation
					int medaillesOr = 0;
					int medaillesArgent = 0;
					int medaillesBronze = 0;

					for (Resultat resultat : delegationResults) {
						if (resultat.getPosition() == 1) {
							medaillesOr++;
						} else if (resultat.getPosition() == 2) {
							medaillesArgent++;
						} else if (resultat.getPosition() == 3) {
							medaillesBronze++;
						}
					}

					// Mettre à jour le nombre de médailles pour la délégation
					Delegation delegation = delegationRepository.findById(delegationId).get();
					delegation.setNombreMedaillesOr(medaillesOr);
					delegation.setNombreMedaillesArgent(medaillesArgent);
					delegation.setNombreMedaillesBronze(medaillesBronze);
					delegationRepository.save(delegation);

					// Convertir la délégation en DelegationDto
					DelegationDto delegationDto = new DelegationDto();
					delegationDto.setId(delegation.getId());
					delegationDto.setNom(delegation.getNom());
					delegationDto.setNombreMedaillesOr(delegation.getNombreMedaillesOr());
					delegationDto.setNombreMedaillesArgent(delegation.getNombreMedaillesArgent());
					delegationDto.setNombreMedaillesBronze(delegation.getNombreMedaillesBronze());
					return delegationDto;
				})
				.sorted(Comparator.comparing(DelegationDto::getNombreMedaillesOr).reversed()
						.thenComparing(DelegationDto::getNombreMedaillesArgent).reversed()
						.thenComparing(DelegationDto::getNombreMedaillesBronze).reversed())
				.collect(Collectors.toList());

		return classementGeneral;
	}
}
