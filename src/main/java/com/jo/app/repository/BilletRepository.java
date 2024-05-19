package com.jo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jo.app.entity.Billet;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.Spectateur;

public interface BilletRepository extends JpaRepository<Billet, Long>{
	/**
     * Compte le nombre de billets vendus pour une épreuve donnée.
     * 
     * @param epreuve L'épreuve pour laquelle compter le nombre de billets vendus.
     * @return Le nombre de billets vendus pour l'épreuve spécifiée.
     */
    int countByEpreuve(Epreuve epreuve);
    
    List<Billet> findAllByEpreuveAndSpectateur(Epreuve epreuve, Spectateur spectateur);
    
    List<Billet> findAllByEpreuve(Epreuve epreuve);

    /**
     * Compte le nombre de billets achetés par un spectateur pour une épreuve donnée.
     * 
     * @param epreuve L'épreuve pour laquelle compter le nombre de billets achetés.
     * @param spectateur Le spectateur pour lequel compter le nombre de billets achetés.
     * @return Le nombre de billets achetés par le spectateur pour l'épreuve spécifiée.
     */
    int countByEpreuveAndSpectateur(Epreuve epreuve, Spectateur spectateur);

    /**
     * Récupère le nombre de billets achetés par un spectateur pour un événement donné.
     * 
     * @param billet Le billet pour lequel rechercher le nombre de billets achetés par un spectateur.
     * @return Le nombre de billets achetés par un spectateur pour l'événement associé au billet.
     */
    default int getTicketsPurchasedBySpectatorForEpreuve(Billet billet) {
    	List<Billet> billets = findAllByEpreuveAndSpectateur(billet.getEpreuve(), billet.getSpectateur());
        return billets.stream().mapToInt(Billet::getQuantite).sum();
    }

    /**
     * Récupère le nombre de billets restants pour un événement donné.
     * 
     * @param billet Le billet pour lequel récupérer le nombre de billets restants.
     * @return Le nombre de billets restants pour l'événement associé au billet.
     */
    default int getRemainingTicketsForEpreuve(Billet billet) {
        int totalTickets = billet.getEpreuve().getNombrePlacesMisesEnVente();
        int soldTickets = findAllByEpreuve(billet.getEpreuve()).stream().mapToInt(Billet::getQuantite).sum();
        return totalTickets - soldTickets;
    }

}
