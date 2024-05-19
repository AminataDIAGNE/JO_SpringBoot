package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.jo.app.dto.EpreuveDto;
import com.jo.app.dto.ParticipantDto;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Participant;
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
	private ParticipantService participantService;

	public ResultatServiceImpl(ResultatRepository resultatRepository, EpreuveService epreuveService, ParticipantService participantService) {
		this.resultatRepository = resultatRepository;
		this.epreuveService = epreuveService;
		this.participantService = participantService;
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

}
