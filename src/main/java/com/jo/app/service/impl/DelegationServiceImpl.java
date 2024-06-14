package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.DelegationDto;
import com.jo.app.entity.Delegation;
import com.jo.app.mapper.DelegationMapper;
import com.jo.app.repository.DelegationRepository;
import com.jo.app.service.DelegationService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DelegationServiceImpl implements DelegationService{

	private DelegationRepository delegationRepository;

    public DelegationServiceImpl(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }

	@Override
	public List<DelegationDto> findAllDelegations() {
		List<Delegation> delegations = delegationRepository.findAll();
        return delegations.stream().map(DelegationMapper::mapToDelegationDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createDelegation(DelegationDto delegationDto) {
		Delegation existingDelegation = delegationRepository.findByNom(delegationDto.getNom()).get();
		if (existingDelegation != null) {
			throw new RuntimeException("delegation exist.");
		}
		Delegation delegation = DelegationMapper.mapToDelegation(delegationDto);
		delegationRepository.save(delegation);
		
	}

	@Override
	public DelegationDto findDelegationById(Long delegationId) {
		Delegation delegation = delegationRepository.findById(delegationId)
                .orElseThrow(() -> new EntityNotFoundException("Délégation non présente dans la BD id: " + delegationId));
        return DelegationMapper.mapToDelegationDto(delegation);
	}

	@Override
	public void updateDelegation(DelegationDto delegationDto) {
		Delegation delegation = DelegationMapper.mapToDelegation(delegationDto);
		delegationRepository.save(delegation);
		
	}

	@Override
	public void deleteDelegation(Long delegationId) {
		delegationRepository.deleteById(delegationId);
		
	}

	@Override
	public List<DelegationDto> findDelegationsByNomContaining(String partialNom) {
		List<Delegation> delegations = delegationRepository.findByNomContaining(partialNom);
        return delegations.stream()
                .map(DelegationMapper::mapToDelegationDto)
                .collect(Collectors.toList());
	}

	@Override
	public boolean existsDelegationByNom(String nom) {
		return delegationRepository.existsByNom(nom);
	}

	@Override
	public List<DelegationDto> findAllDelegationsOrderedByName() {
		List<Delegation> delegations = delegationRepository.findAllByOrderByNom();
        return delegations.stream()
                .map(DelegationMapper::mapToDelegationDto)
                .collect(Collectors.toList());
	}

	@Override
	public long countDelegations() {
		return delegationRepository.count();
	}

}
