package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.ResultatDto;
import com.jo.app.entity.Resultat;
import com.jo.app.mapper.ResultatMapper;
import com.jo.app.repository.ResultatRepository;
import com.jo.app.service.Resultatervice;

@Service
public class ResultatServiceImpl implements Resultatervice{

	private ResultatRepository resultatRepository;

    public ResultatServiceImpl(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

	@Override
	public List<ResultatDto> findAllResultats() {
		List<Resultat> resultats = resultatRepository.findAll();
        return resultats.stream().map(ResultatMapper::mapToResultatDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createResultat(ResultatDto resultatDto) {
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
