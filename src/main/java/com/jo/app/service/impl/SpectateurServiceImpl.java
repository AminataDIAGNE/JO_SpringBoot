package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.SpectateurDto;
import com.jo.app.entity.Spectateur;
import com.jo.app.mapper.SpectateurMapper;
import com.jo.app.repository.SpectateurRepository;
import com.jo.app.service.SpectateurService;

@Service
public class SpectateurServiceImpl implements SpectateurService{
    
	private SpectateurRepository spectateurRepository;

    public SpectateurServiceImpl(SpectateurRepository spectateurRepository) {
        this.spectateurRepository = spectateurRepository;
    }

	@Override
	public List<SpectateurDto> findAllSpectateurs() {
		List<Spectateur> spectateurs = spectateurRepository.findAll();
        return spectateurs.stream().map(SpectateurMapper::mapToSpectateurDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createSpectateur(SpectateurDto spectateurDto) {
		Spectateur spectateur = SpectateurMapper.mapToSpectateur(spectateurDto);
		spectateurRepository.save(spectateur);
		
	}

	@Override
	public SpectateurDto findSpectateurById(Long spectateurId) {
		Spectateur spectateur = spectateurRepository.findById(spectateurId).get();
		return SpectateurMapper.mapToSpectateurDto(spectateur);
	}

	@Override
	public void updateSpectateur(SpectateurDto spectateurDto) {
		Spectateur spectateur = SpectateurMapper.mapToSpectateur(spectateurDto);
		spectateurRepository.save(spectateur);
		
	}

	@Override
	public void deleteSpectateur(Long spectateurId) {
		spectateurRepository.deleteById(spectateurId);
		
	}


}
