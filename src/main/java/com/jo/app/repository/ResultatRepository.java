package com.jo.app.repository;

import java.util.List;

import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jo.app.entity.Resultat;

public interface ResultatRepository extends JpaRepository<Resultat, Long>{

	/**
	 * 
	 * @param participantId
	 * 		  identifiant de la delegation
	 * 
	 * @return recherche et renvoie une liste de resultats d'un participant spécifique, identifiée par son identifiant
	 */
	List<Resultat> findResultatsByparticipantId(Long participantId);

	List<Resultat> findByEpreuveAndParticipant(Epreuve epreuve, Participant participant);
}
