package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.InfrastructureSportiveDto;
import com.jo.app.entity.InfrastructureSportive;
import com.jo.app.mapper.InfrastructureSportiveMapper;
import com.jo.app.repository.InfrastructureSportiveRepository;
import com.jo.app.service.InfrastructureSportiveService;

@Service
public class InfrastructureSportiveServiceImpl implements InfrastructureSportiveService{

	private InfrastructureSportiveRepository infrastructureSportiveRepository;

    public InfrastructureSportiveServiceImpl(InfrastructureSportiveRepository infrastructureSportiveRepository) {
        this.infrastructureSportiveRepository = infrastructureSportiveRepository;
    }

	@Override
	public List<InfrastructureSportiveDto> findAllInfrastructureSportives() {
		List<InfrastructureSportive> infrastructureSportives = infrastructureSportiveRepository.findAll();
        return infrastructureSportives.stream().map(InfrastructureSportiveMapper::mapToInfrastructureSportiveDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createInfrastructureSportive(InfrastructureSportiveDto infrastructureSportiveDto) {
		InfrastructureSportive infrastructureSportive = InfrastructureSportiveMapper.mapToInfrastructureSportive(infrastructureSportiveDto);
		infrastructureSportiveRepository.save(infrastructureSportive);
		
	}

	@Override
	public InfrastructureSportiveDto findInfrastructureSportiveById(Long infrastructureSportiveId) {
		InfrastructureSportive infrastructureSportive = infrastructureSportiveRepository.findById(infrastructureSportiveId).get();
		return InfrastructureSportiveMapper.mapToInfrastructureSportiveDto(infrastructureSportive);
	}

	@Override
	public void updateInfrastructureSportive(InfrastructureSportiveDto infrastructureSportiveDto) {
		InfrastructureSportive infrastructureSportive = InfrastructureSportiveMapper.mapToInfrastructureSportive(infrastructureSportiveDto);
		infrastructureSportiveRepository.save(infrastructureSportive);
		
	}

	@Override
	public void deleteInfrastructureSportive(Long infrastructureSportiveId) {
		infrastructureSportiveRepository.deleteById(infrastructureSportiveId);
		
	}

}
