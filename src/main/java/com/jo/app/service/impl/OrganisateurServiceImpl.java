package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.OrganisateurDto;
import com.jo.app.entity.Organisateur;
import com.jo.app.mapper.OrganisateurMapper;
import com.jo.app.repository.OrganisateurRepository;
import com.jo.app.service.OrganisateurService;

@Service
public class OrganisateurServiceImpl implements OrganisateurService{

	private OrganisateurRepository organisateurRepository;

    public OrganisateurServiceImpl(OrganisateurRepository organisateurRepository) {
        this.organisateurRepository = organisateurRepository;
    }

	@Override
	public List<OrganisateurDto> findAllOrganisateurs() {
		List<Organisateur> organisateurs = organisateurRepository.findAll();
        return organisateurs.stream().map(OrganisateurMapper::mapToOrganisateurDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createOrganisateur(OrganisateurDto organisateurDto) {
		Organisateur organisateur = OrganisateurMapper.mapToOrganisateur(organisateurDto);
		organisateurRepository.save(organisateur);
		
	}

	@Override
	public OrganisateurDto findOrganisateurById(Long organisateurId) {
		Organisateur organisateur = organisateurRepository.findById(organisateurId).get();
		return OrganisateurMapper.mapToOrganisateurDto(organisateur);
	}

	@Override
	public void updateOrganisateur(OrganisateurDto organisateurDto) {
		Organisateur organisateur = OrganisateurMapper.mapToOrganisateur(organisateurDto);
		organisateurRepository.save(organisateur);
		
	}

	@Override
	public void deleteOrganisateur(Long organisateurId) {
		organisateurRepository.deleteById(organisateurId);
		
	}

}
