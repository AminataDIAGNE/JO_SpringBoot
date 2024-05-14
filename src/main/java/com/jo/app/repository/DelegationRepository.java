package com.jo.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jo.app.entity.Delegation;

public interface DelegationRepository extends JpaRepository<Delegation, Long>{

	/**
	 * 
	 * @param nom
	 * @return urechercher ne délégation par son nom
	 */
	Optional<Delegation> findByNom(String nom);
	/**
	 * 
	 * @param partialNom
	 * @return rechercher les délégations par un nom partiel 
	 */
	List<Delegation> findByNomContaining(String partialNom);
	
	/**
	 * 
	 * @return obtenir toutes les délégations triées par leur nom 
	 */
	List<Delegation> findAllByOrderByNom();
	
	/**
	 * le nombre total de délégations 
	 */
	long count();
	
	/**
	 * 
	 * @param nom
	 * @return vérifier si une délégation existe par son nom 
	 */
	boolean existsByNom(String nom);

}
