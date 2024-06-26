package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.BilletDto;
import com.jo.app.entity.Billet;

public interface BilletService {

	/**
     * Récupère tous les billets.
     * 
     * @return Une liste contenant tous les billets.
     */
    List<BilletDto> findAllBillets();

    /**
     * Crée un nouveau billet.
     * 
     * @param billetDto Les informations du billet à créer.
     */
    void createBillet(BilletDto billetDto);

    /**
     * Récupère un billet par son identifiant.
     * 
     * @param billetId L'identifiant du billet à récupérer.
     * @return Les informations du billet trouvé.
     */
    BilletDto findBilletById(Long billetId);

    /**
     * Met à jour un billet existant.
     * 
     * @param billetDto Les nouvelles informations du billet.
     */
    void updateBillet(BilletDto billetDto);

    /**
     * Achat du billet.
     *
     * @param billetDto Les nouvelles informations du billet.
     */
    void achat(BilletDto billetDto);

    /**
     * Met à jour un billet existant lors du controlle.
     *
     * @param billetDto Les nouvelles informations du billet.
     */
    void controle(BilletDto billetDto);
    /**
     * Supprime un billet par son identifiant.
     * 
     * @param billetId L'identifiant du billet à supprimer.
     */
    void deleteBillet(Long billetId);
    
    /**
     * Récupère le nombre de billets achetés par un spectateur pour un événement donné.
     * 
     * @param billet Le billet pour lequel rechercher le nombre de billets achetés par un spectateur.
     * @return Le nombre de billets achetés par un spectateur pour l'événement associé au billet.
     */
    int getTicketsPurchasedBySpectatorForEpreuve(Billet billet);
    
    /**
     * Récupère le nombre de billets restants pour un événement donné.
     * 
     * @param billet Le billet pour lequel récupérer le nombre de billets restants.
     * @return Le nombre de billets restants pour l'événement associé au billet.
     */
    int getRemainingTicketsForEpreuve(Billet billet);
    
    /**
     * Récupère le nombre de billets vendus pour un événement donné.
     * 
     * @param billet Le billet pour lequel rechercher le nombre de billets vendus.
     * @return Le nombre de billets vendus pour l'événement associé au billet.
     */
    int getSoldTicketsForEpreuve(Billet billet);
    
    /**
     * Annule un billet en mettant à jour son état.
     * 
     * @param billetId L'identifiant du billet à annuler.
     * @return Les informations du billet annulé.
     * @throws RuntimeException Si le billet avec l'identifiant spécifié n'est pas trouvé.
     */
    BilletDto cancelBillet(Long billetId);

}
