package com.jo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jo.app.entity.Participant;

/**
 * Spring Data JPA va automatiquement générer une requête
 * Pour plus de flexibilité vous pouvez ecrire votre propre requete JPQL via @Query("")
 */
public interface ParticipantRepository extends JpaRepository<Participant, Long>{
	
	/**
	 * 
	 * @param delegationId
	 * 		  identifiant de la delegation
	 * 
	 * @return recherche et renvoie une liste de participants appartenant à une délégation spécifique, identifiée par son identifiant
	 */
	List<Participant> findParticipantsByDelegationId(Long delegationId);
	 
	/**
	 * 
	 * @param nom
	 * @return recherche et renvoie une liste de participants ayant un nom spécifique.
	 */
	List<Participant> findParticipantsByNom(String nom); 
	
	/**
	 * 
	 * @param nom
	 * @param delegationId
	 * @return recherche et renvoie une liste de participants ayant un nom spécifique et appartenant à une délégation spécifique.
	 */
	@Query("SELECT p FROM Participant p WHERE p.nom = :nom AND p.delegation.id = :delegationId")
	List<Participant> findParticipantsByNomAndDelegation(String nom, Long delegationId);

	
	/**
	 * 
	 * @param partialNom
	 * @return recherche et renvoie une liste de participants dont le nom contient une sous-chaîne spécifique.
	 */
	List<Participant> findParticipantsByNomContaining(String partialNom);
	
	/**
	 * 
	 * @param nom
	 * @return recherche et renvoie une liste de participants ayant un nom spécifique, en ignorant la casse.
	 */
	List<Participant> findParticipantsByNomIgnoreCase(String nom);

	/**
	 * 
	 * @param nom
	 * @return vérifie si un participant avec un nom spécifique existe.
	 */
	boolean existsByNom(String nom);
	
	/**
     * Vérifie si un participant avec un nom spécifique existe dans une délégation spécifique.
     *
     * @param nom Le nom du participant à vérifier.
     * @param delegationId L'identifiant de la délégation dans laquelle vérifier l'existence du participant.
     * @return true si un participant avec le nom spécifié existe dans la délégation spécifiée, sinon false.
     */
    boolean existsByNomAndDelegationId(String nom, Long delegationId);
	
	/**
	 * 
	 * @return renvoie une liste de tous les participants, triés par ordre alphabétique du nom.
	 */
	List<Participant> findAllByOrderByNom();
	
	/**
	 * 
	 * @param delegationId
	 * @return compte le nombre total de participants appartenant à une délégation spécifique.
	 */
	long countByDelegationId(Long delegationId);

	
	/**
	 *
	 * @param email
	 * @return recherche et renvoie un participant ayant un email spécifique.
	 */
	Participant findByEmail(String email);


}
