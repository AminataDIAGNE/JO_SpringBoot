package com.jo.app.service.impl;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jo.app.repository.EpreuveRepository;
import com.jo.app.repository.SpectateurRepository;
import org.springframework.stereotype.Service;

import com.jo.app.dto.BilletDto;
import com.jo.app.dto.EpreuveDto;
import com.jo.app.dto.SpectateurDto;
import com.jo.app.entity.Billet;
import com.jo.app.mapper.BilletMapper;
import com.jo.app.mapper.EpreuveMapper;
import com.jo.app.mapper.SpectateurMapper;
import com.jo.app.repository.BilletRepository;
import com.jo.app.service.BilletService;
import com.jo.app.util.Etat;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BilletServiceImpl implements BilletService{

	private BilletRepository billetRepository;
	private EpreuveRepository epreuveRepository;

	private SpectateurRepository spectateurRepository;

    public BilletServiceImpl(BilletRepository billetRepository ,EpreuveRepository epreuveRepository ,SpectateurRepository spectateurRepository) {
        this.billetRepository = billetRepository;
		this.epreuveRepository =epreuveRepository;
		this.spectateurRepository = spectateurRepository;
    }

	@Override
	public List<BilletDto> findAllBillets() {
		List<Billet> billets = billetRepository.findAll();
        return billets.stream().map(BilletMapper::mapToBilletDto)
                .collect(Collectors.toList());
	}

	@Override
	public BilletDto createBillet(BilletDto billetDto) {
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
	    	billet.setEtat(Etat.EN_ATTENTE);
	    	billet.setPrixTotal(billet.getEpreuve().getPrixUnitaireBillet()* billet.getQuantite());
	    	return BilletMapper.mapToBilletDto(billetRepository.save(billet));
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

	/*@Override
	public BilletDto achat(BilletDto billetDto) {
		
	    Billet billet =  BilletMapper.mapToBillet(createBillet(billetDto));

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
			return BilletMapper.mapToBilletDto(billetRepository.save(billet));
		}

	}*/

	@Override
	public void controle(BilletDto billetDto) {
		Billet billet = BilletMapper.mapToBillet(billetDto);

		if (billetDto.getEtat() != Etat.VALIDE) {
			throw new RuntimeException("Billet non valide");
		}
		// Date de l'épreuve dépassé
		else if(ChronoUnit.DAYS.between(LocalDateTime.now() ,
				billetDto.getEpreuve().getDate() ) == 0
		){
			throw new RuntimeException("Billet plus valide");
		}
		else {
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
		Billet billetN = billetRepository.findById(billetId)
				.orElseThrow(() -> new EntityNotFoundException("Billet non présente dans la BD [ id: " + billetId+" ]"));
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
		Billet cancelledBillet = billetRepository.save(billet);
		return BilletMapper.mapToBilletDto(cancelledBillet);
	}

	//Todo à tester
	@Override
	public List<BilletDto> venteStatistiques() {
		List<Billet> billets = billetRepository.findAll();
		List<BilletDto> billetDtos = new ArrayList<>();

		for (Billet billet : billets) {
			BilletDto billetDto = BilletMapper.mapToBilletDto(billet);
			if (billetDto.getEtat() == Etat.VALIDE) {
				billetDtos.add(billetDto);
			}
		}

		return billetDtos;
	}

	@Override
	public List<BilletDto> venteStatistiquesParEpreuve(Long epreuveId) {
		List<Billet> billets = billetRepository.findAll();
		List<BilletDto> billetDtos = new ArrayList<>();

		for (Billet billet : billets) {
			BilletDto billetDto = BilletMapper.mapToBilletDto(billet);
			if (billetDto.getEtat() == Etat.VALIDE && billetDto.getIdEpreuve().equals(epreuveId)) {
				billetDtos.add(billetDto);
			}
		}

		return billetDtos;
	}

	@Override
	public BilletDto reserverBillet(Long idSpectateur, Long idEpreuve, int quantite) {
		Billet billet = new Billet();
		billet.setSpectateur(spectateurRepository.findById(idSpectateur).orElseThrow(() -> new IllegalStateException("Spectateur non trouvé")));
		billet.setEpreuve(epreuveRepository.findById(idEpreuve).orElseThrow(() -> new IllegalStateException("Epreuve non trouvée")));
		billet.setQuantite(quantite);

		final int MAX_TICKETS_PER_SPECTATOR = 4;

		if (quantite <= 0 || quantite > MAX_TICKETS_PER_SPECTATOR) {
			throw new IllegalStateException("Le nombre de billets qu'un spectateur peut acheter doit être compris entre 1 et 4 compris");
		}

		int remainingTickets = getRemainingTicketsForEpreuve(billet);
		int purchasedTicketsBySpectator = getTicketsPurchasedBySpectatorForEpreuve(billet);

		int availableTickets = Math.max(0, MAX_TICKETS_PER_SPECTATOR - purchasedTicketsBySpectator);

		if (remainingTickets - quantite <= 0) {
			throw new IllegalStateException("Impossible de créer le billet. Il n'y a plus assez de billets disponibles pour cet épreuve.");
		} else if (billet.getQuantite() > availableTickets) {
			throw new IllegalStateException("Impossible de créer le billet. Le nombre de billets demandés (" + billet.getQuantite() + ") "
					+ "dépasse la limite autorisée par spectateur (" + MAX_TICKETS_PER_SPECTATOR + ").");
		} else {
			billet.setEtat(Etat.EN_ATTENTE);
			billet.setPrixTotal(billet.getEpreuve().getPrixUnitaireBillet() * billet.getQuantite());
			billet.setMontantRemboursement(-1);
			billet.setMessageConfirmation("Pas encore d'annulation");

			return BilletMapper.mapToBilletDto(billetRepository.save(billet));
		}
	}
	@Override
	public BilletDto confirmerPaiement(Long billetId) {
		Billet billet = billetRepository.findById(billetId).orElseThrow(() -> new IllegalStateException("Billet non trouvé"));

		if (billet.getEtat() != Etat.EN_ATTENTE) {
			throw new IllegalStateException("Le billet n'est pas en attente de paiement ou a déjà été confirmé.");
		}

		billet.setEtat(Etat.VALIDE);
		return BilletMapper.mapToBilletDto(billetRepository.save(billet));
	}


	@Override
	public List<BilletDto> findAllByEpreuveAndSpectateur(EpreuveDto epreuveDto, SpectateurDto spectateurDto) {
		List<Billet> billets = billetRepository.findAllByEpreuveAndSpectateur(EpreuveMapper.mapToEpreuve(epreuveDto), SpectateurMapper.mapToSpectateur(spectateurDto));
        return billets.stream().map(BilletMapper::mapToBilletDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<BilletDto> findAllByEpreuve(EpreuveDto epreuveDto) {
		List<Billet> billets = billetRepository.findAllByEpreuve(EpreuveMapper.mapToEpreuve(epreuveDto));
        return billets.stream().map(BilletMapper::mapToBilletDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<BilletDto> findAllBySpectateur(SpectateurDto spectateurDto) {
		List<Billet> billets = billetRepository.findAllBySpectateur(SpectateurMapper.mapToSpectateur(spectateurDto));
        return billets.stream().map(BilletMapper::mapToBilletDto)
                .collect(Collectors.toList());
	}

	@Override
	public void updateEtat(BilletDto billetDto) {
		billetRepository.save(BilletMapper.mapToBillet(billetDto));
	}

}
