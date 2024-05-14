package com.jo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jo.app.entity.Resultat;

public interface ResultatRepository extends JpaRepository<Resultat, Long>{

	/**
	 * 
	 * @param participantID
	 * 		  identifiant de la delegation
	 * 
	 * @return recherche et renvoie une liste de resultats d'un participant spécifique, identifiée par son identifiant
	 */
	List<Resultat> findResultatsByparticipantId(Long participantId);
}
