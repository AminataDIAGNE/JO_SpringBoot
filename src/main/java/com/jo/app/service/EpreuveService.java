package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.EpreuveDto;

public interface EpreuveService {

	List<EpreuveDto> findAllEpreuves();

    void createEpreuve(EpreuveDto epreuveDto);

    EpreuveDto findEpreuveById(Long epreuveId);

    void updateEpreuve(EpreuveDto epreuveDto);

    void deleteEpreuve(Long epreuveId);

}
