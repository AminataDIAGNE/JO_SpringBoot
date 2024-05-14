package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.EpreuveDto;
import com.jo.app.entity.Epreuve;
import com.jo.app.mapper.EpreuveMapper;
import com.jo.app.repository.EpreuveRepository;
import com.jo.app.service.EpreuveService;

@Service
public class EpreuveServiceImpl implements EpreuveService{

	private EpreuveRepository epreuveRepository;

    public EpreuveServiceImpl(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }

	@Override
	public List<EpreuveDto> findAllEpreuves() {
		List<Epreuve> epreuves = epreuveRepository.findAll();
        return epreuves.stream().map(EpreuveMapper::mapToEpreuveDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createEpreuve(EpreuveDto epreuveDto) {
		Epreuve epreuve = EpreuveMapper.mapToEpreuve(epreuveDto);
		epreuveRepository.save(epreuve);
		
	}

	@Override
	public EpreuveDto findEpreuveById(Long epreuveId) {
		Epreuve epreuve = epreuveRepository.findById(epreuveId).get();
		return EpreuveMapper.mapToEpreuveDto(epreuve);
	}

	@Override
	public void updateEpreuve(EpreuveDto epreuveDto) {
		Epreuve epreuve = EpreuveMapper.mapToEpreuve(epreuveDto);
		epreuveRepository.save(epreuve);
		
	}

	@Override
	public void deleteEpreuve(Long epreuveId) {
		epreuveRepository.deleteById(epreuveId);
		
	}

}
