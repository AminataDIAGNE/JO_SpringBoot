package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.DelegationDto;
import com.jo.app.dto.ResultatDto;

public interface ResultatService {

	List<ResultatDto> findAllResultats();
	
	List<ResultatDto> findResultatsByParticipant(Long idParticipant);

    void createResultat(ResultatDto resultatDto);

    ResultatDto findResultatById(Long resultatId);

    void updateResultat(ResultatDto resultatDto);

    void deleteResultat(Long resultatId);

    List<DelegationDto> calculerClassementGeneral();
}
