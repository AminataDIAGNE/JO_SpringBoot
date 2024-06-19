package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.BilletDto;
import com.jo.app.dto.EpreuveDto;
import com.jo.app.dto.SpectateurDto;
import com.jo.app.entity.Billet;

public interface BilletService {

	/**
     * Récupère tous les billets.
     * 
     * @return Une liste contenant tous les billets.
     */
    List<BilletDto> findAllBillets();
    
    List<BilletDto> findAllByEpreuveAndSpectateur(EpreuveDto epreuveDto, SpectateurDto spectateurDto);
    
    List<BilletDto> findAllByEpreuve(EpreuveDto epreuveDto);
    
    List<BilletDto> findAllBySpectateur(SpectateurDto spectateurDto);

    /**
     * Crée un nouveau billet.
     * 
     * @param billetDto Les informations du billet à créer.
     */
    BilletDto createBillet(BilletDto billetDto);
    
    void updateEtat(BilletDto billetDto);

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

    /**
     * Remonte l'ensemble des billets vendus
     *
     * @return Les informations du billet Valide.
     * @throws RuntimeException Si le billet avec l'identifiant spécifié n'est pas trouvé.
     */

    List<BilletDto> venteStatistiques();

    public List<BilletDto> venteStatistiquesParEpreuve(Long epreuveId);

    /**
     * Reservation d'un billet en faisant directement  un nouveau billet.
     *Les informations du billet à créer.
     */

    BilletDto reserverBillet(Long idSpectateur, Long idEpreuve, int quantite);

    BilletDto confirmerPaiement(Long billetId);
}
