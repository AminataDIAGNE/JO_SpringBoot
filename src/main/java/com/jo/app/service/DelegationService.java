package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.DelegationDto;

public interface DelegationService {

	/**
     * Récupérer toutes les délégations.
     *
     * @return Une liste contenant toutes les délégations.
     */
    List<DelegationDto> findAllDelegations();

    /**
     * Créer une nouvelle délégation.
     *
     * @param delegationDto Les informations de la délégation à créer.
     */
    void createDelegation(DelegationDto delegationDto);

    /**
     * Récupérer une délégation par son identifiant.
     *
     * @param delegationId L'identifiant de la délégation à récupérer.
     * @return Les informations de la délégation correspondante.
     */
    DelegationDto findDelegationById(Long delegationId);

    /**
     * Mettre à jour une délégation existante.
     *
     * @param delegationDto Les nouvelles informations de la délégation.
     */
    void updateDelegation(DelegationDto delegationDto);

    /**
     * Supprimer une délégation.
     *
     * @param delegationId L'identifiant de la délégation à supprimer.
     */
    void deleteDelegation(Long delegationId);

    /**
     * Rechercher les délégations dont le nom contient une sous-chaîne spécifique.
     *
     * @param partialNom La sous-chaîne à rechercher dans les noms de délégations.
     * @return Une liste contenant les délégations correspondantes.
     */
    List<DelegationDto> findDelegationsByNomContaining(String partialNom);

    /**
     * Vérifier si une délégation existe par son nom.
     *
     * @param nom Le nom de la délégation à vérifier.
     * @return true si une délégation avec le nom spécifié existe, sinon false.
     */
    boolean existsDelegationByNom(String nom);

    /**
     * Récupérer toutes les délégations triées par leur nom.
     *
     * @return Une liste de toutes les délégations triées par ordre alphabétique du nom.
     */
    List<DelegationDto> findAllDelegationsOrderedByName();

    /**
     * Compter le nombre total de délégations.
     *
     * @return Le nombre total de délégations.
     */
    long countDelegations();
}
