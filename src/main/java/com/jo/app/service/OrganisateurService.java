package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.OrganisateurDto;

public interface OrganisateurService {

	List<OrganisateurDto> findAllOrganisateurs();

    void createOrganisateur(OrganisateurDto organisateurDto);

    OrganisateurDto findOrganisateurById(Long organisateurId);

    void updateOrganisateur(OrganisateurDto organisateurDto);

    void deleteOrganisateur(Long organisateurId);

}
