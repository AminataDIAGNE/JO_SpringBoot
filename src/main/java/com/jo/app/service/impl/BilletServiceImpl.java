package com.jo.app.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.BilletDto;
import com.jo.app.entity.Billet;
import com.jo.app.mapper.BilletMapper;
import com.jo.app.repository.BilletRepository;
import com.jo.app.service.BilletService;
import com.jo.app.util.Etat;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BilletServiceImpl implements BilletService{

	private BilletRepository billetRepository;

    public BilletServiceImpl(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

	@Override
	public List<BilletDto> findAllBillets() {
		List<Billet> billets = billetRepository.findAll();
        return billets.stream().map(BilletMapper::mapToBilletDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createBillet(BilletDto billetDto) {
	    Billet billet = BilletMapper.mapToBillet(billetDto);
	    final int MAX_TICKETS_PER_SPECTATOR = 4;
	    
	    if (billet.getQuantite() <= 0 || billet.getQuantite() > MAX_TICKETS_PER_SPECTATOR) {
	        throw new IllegalStateException("Le nombre de billets qu'un spectateur peut acheter doit être compris entre 1 et 4 compris");
	    }
	    
	    int remainingTickets = getRemainingTicketsForEpreuve(billet);
	    int purchasedTicketsBySpectator = getTicketsPurchasedBySpectatorForEpreuve(billet);
	    
	    int availableTickets = Math.max(0, MAX_TICKETS_PER_SPECTATOR - purchasedTicketsBySpectator);
	    
	    if (remainingTickets <= 0) {
	        throw new IllegalStateException("Impossible de créer le billet. Il n'y a plus de billets disponibles pour cet épreuve.");
	        
	    } else if (billet.getQuantite() > availableTickets) {
	    	throw new IllegalStateException("Impossible de créer le billet. Le nombre de billets demandés (" + billet.getQuantite() + ") "
	    			+ "dépasse la limite autorisée par spectateur (" + MAX_TICKETS_PER_SPECTATOR + ").");
	    	
	    } else {
	    	billet.setEtat(Etat.VALIDE);
	    	billet.setPrixTotal(billet.getEpreuve().getPrixUnitaireBillet()* billet.getQuantite());
	        billetRepository.save(billet);
	    }
	}

	@Override
	public BilletDto findBilletById(Long billetId) {
		Billet billet = billetRepository.findById(billetId)
				.orElseThrow(() -> new EntityNotFoundException("Billet non présente dans la BD [ id: " + billetId+" ]"));
		return BilletMapper.mapToBilletDto(billet);
	}

	@Override
	public void updateBillet(BilletDto billetDto) {
		Billet billet = BilletMapper.mapToBillet(billetDto);
		final int MAX_TICKETS_PER_SPECTATOR = 4;

		if (billet.getQuantite() <= 0 || billet.getQuantite() > MAX_TICKETS_PER_SPECTATOR) {
			throw new RuntimeException("Le nombre de billets qu'un spectateur peut acheter doit être compris entre 1 et 4 inclu");
		}
		int remainingTickets = getRemainingTicketsForEpreuve(billet);
		int purchasedTicketsBySpectator = getTicketsPurchasedBySpectatorForEpreuve(billet) - findBilletById(billetDto.getId()).getQuantite();

		int availableTickets = Math.max(0, MAX_TICKETS_PER_SPECTATOR - purchasedTicketsBySpectator);

		if (remainingTickets <= 0) {
			throw new RuntimeException("Impossible de mettre à jour le billet. Il n'y a plus de billets disponibles pour cet épreuve.");

		} else if (billet.getQuantite() > availableTickets) {
			throw new RuntimeException("Impossible de mettre à jour le billet. Le nombre de billets demandés (" + billet.getQuantite() + ") "
					+ "dépasse la limite autorisée par spectateur (" + MAX_TICKETS_PER_SPECTATOR + ").");

		} else {
			billet.setPrixTotal(billet.getEpreuve().getPrixUnitaireBillet() * billet.getQuantite());
			billetRepository.save(billet);
		}
	}

	@Override
	public void achat(BilletDto billetDto) {
	    Billet billet = BilletMapper.mapToBillet(billetDto);
	    final int MAX_TICKETS_PER_SPECTATOR = 4;
	    
	    if (billet.getQuantite() <= 0 || billet.getQuantite() > MAX_TICKETS_PER_SPECTATOR) {
	        throw new RuntimeException("Le nombre de billets qu'un spectateur peut acheter doit être compris entre 1 et 4 inclu");
	    }
	    int remainingTickets = getRemainingTicketsForEpreuve(billet);
	    int purchasedTicketsBySpectator = getTicketsPurchasedBySpectatorForEpreuve(billet) - findBilletById(billetDto.getId()).getQuantite();
	    
	    int availableTickets = Math.max(0, MAX_TICKETS_PER_SPECTATOR - purchasedTicketsBySpectator);
	    
	    if (remainingTickets <= 0) {
	        throw new RuntimeException("Impossible de mettre à jour le billet. Il n'y a plus de billets disponibles pour cet épreuve.");
	        
	    } else if (billet.getQuantite() > availableTickets) {
	        throw new RuntimeException("Impossible de mettre à jour le billet. Le nombre de billets demandés (" + billet.getQuantite() + ") "
	        		+ "dépasse la limite autorisée par spectateur (" + MAX_TICKETS_PER_SPECTATOR + ").");
	        
	    } else {
	    	billet.setEtat(Etat.VALIDE);
	    	billet.setPrixTotal(billet.getEpreuve().getPrixUnitaireBillet() * billet.getQuantite());
	        billetRepository.save(billet);
	    }
	}

	@Override
	public void controle(BilletDto billetDto) {
		Billet billet = BilletMapper.mapToBillet(billetDto);

		if (billetDto.getEtat() != Etat.VALIDE) {
			throw new RuntimeException("Billet non valide");
		} else {
			billet.setEtat(Etat.INVALIDE);
			billetRepository.save(billet);
		}
	}

	@Override
	public void deleteBillet(Long billetId) {
		billetRepository.deleteById(billetId);
		
	}

	@Override
	public int getTicketsPurchasedBySpectatorForEpreuve(Billet billet) {
		return billetRepository.getTicketsPurchasedBySpectatorForEpreuve(billet);
	}

	// le nombre de billet restant d'une epreuve

	@Override
	public int getRemainingTicketsForEpreuve(Billet billet) {
        return billetRepository.getRemainingTicketsForEpreuve(billet);
	}

	@Override
	public int getSoldTicketsForEpreuve(Billet billet) {
		return billetRepository.countByEpreuve(billet.getEpreuve());
	}
	
	@Override
	public BilletDto cancelBillet(Long billetId) {
	    Billet billet = BilletMapper.mapToBillet(findBilletById(billetId));

		//process de remboursement
		// Vérifier les conditions de date pour l'annulation
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime dateEpreuve = billet.getEpreuve().getDate(); // Assurez-vous que l'entité Epreuve a un champ date de type LocalDate
		long daysBetween = ChronoUnit.DAYS.between(currentDate, dateEpreuve);

		// Initialiser le montant de remboursement
		double montantRemboursement = 0;
		String messageConfirmation;

		if (daysBetween > 7) {
			// Annulation sans frais
			montantRemboursement = billet.getPrixTotal();
			messageConfirmation = "Annulation sans frais. Montant remboursé: " + montantRemboursement + " EUR";
		} else if (daysBetween >= 3) {
			// Remboursement à 50%
			montantRemboursement = billet.getPrixTotal() * 0.5;
			messageConfirmation = "Remboursement à 50%. Montant remboursé: " + montantRemboursement + " EUR";
		} else {
			// Annulation non possible après 3 jours
			throw new IllegalStateException("Annulation non autorisée : moins de 3 jours avant la date de l'épreuve");
		}

		billet.setMontantRemboursement(montantRemboursement);
		billet.setMessageConfirmation(messageConfirmation);

		billet.setEtat(Etat.ANNULE);
		billet.setQuantite(0);
		Billet cancelledBillet = billetRepository.save(billet);
		return BilletMapper.mapToBilletDto(cancelledBillet);
	}

}
