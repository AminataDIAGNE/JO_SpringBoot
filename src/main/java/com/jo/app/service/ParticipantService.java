package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.EpreuveDto;
import com.jo.app.dto.ParticipantDto;

public interface ParticipantService {

	/**
     * Récupérer tous les participants.
     *
     * @return Une liste contenant tous les participants.
     */
    List<ParticipantDto> findAllParticipants();

    /**
     * Rechercher les participants par nom.
     *
     * @param nom Le nom des participants à rechercher.
     * @return Une liste contenant les participants ayant le nom spécifié.
     */
    List<ParticipantDto> findAllParticipantsByNom(String nom);

    /**
     * Créer un nouveau participant.
     *
     * @param participantDto Les informations du participant à créer.
     */
    void createParticipant(ParticipantDto participantDto);
    
    /**
     * Récupérer un participant par son identifiant.
     *
     * @param participantId L'identifiant du participant à récupérer.
     * @return Les informations du participant correspondant.
     * @throws EntityNotFoundException si aucun participant correspondant n'est trouvé.
     */
    ParticipantDto findParticipantById(Long participantId);

    /**
     * Mettre à jour un participant existant.
     *
     * @param participantDto Les nouvelles informations du participant.
     */
    void updateParticipant(ParticipantDto participantDto);

    /**
     * Supprimer un participant.
     *
     * @param participantId L'identifiant du participant à supprimer.
     */
    void deleteParticipant(Long participantId);
    
    /**
     * Recherche les participants par leur nom et l'identifiant de la délégation à laquelle ils appartiennent.
     *
     * @param nom Le nom du participant à rechercher.
     * @param delegationId L'identifiant de la délégation à laquelle le participant doit appartenir.
     * @return Une liste de participants correspondant aux critères de recherche.
     */
    List<ParticipantDto> findParticipantsByNomAndDelegation(String nom, Long delegationId);
    
    /**
     * Recherche les participants dont le nom contient une sous-chaîne spécifique.
     *
     * @param partialNom La sous-chaîne spécifique à rechercher dans le nom des participants.
     * @return Une liste de participants dont le nom contient la sous-chaîne spécifique.
     */
    List<ParticipantDto> findParticipantsByNomContaining(String partialNom);
    
    /**
     * Renvoie une liste de tous les participants, triés par ordre alphabétique du nom.
     *
     * @return Une liste de participants triés par ordre alphabétique du nom.
     */
    List<ParticipantDto> findAllParticipantsOrderedByName();
    
    /**
    * Compte le nombre total de participants appartenant à une délégation spécifique.
    *
    * @param delegationId L'identifiant de la délégation pour laquelle compter les participants.
    * @return Le nombre total de participants appartenant à la délégation spécifiée.
    */
   long countParticipantsByDelegationId(Long delegationId);

   /**
    * Vérifie si un participant avec un nom spécifique existe.
    *
    * @param nom Le nom du participant à vérifier.
    * @return true si un participant avec le nom spécifié existe, sinon false.
    */
   boolean existsParticipantByNom(String nom);
   
   /**
    * Vérifie si un participant avec un nom spécifique existe dans une délégation spécifique.
    *
    * @param nom Le nom du participant à vérifier.
    * @param delegationId L'identifiant de la délégation dans laquelle vérifier l'existence du participant.
    * @return true si un participant avec le nom spécifié existe dans la délégation spécifiée, sinon false.
    */
   boolean existsParticipantByNomAndDelegationId(String nom, Long delegationId);

}
