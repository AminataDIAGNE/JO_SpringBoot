package com.jo.app.entity;

import com.jo.app.util.Etat;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "billet")
public class Billet {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "epreuve_id")
    private Epreuve epreuve;
    
    @ManyToOne
    @JoinColumn(name = "spectateur_id")
    private Spectateur spectateur;
    
    private double prixTotal;
    
    private int quantite;
    
    private Etat etat; // pour la gestion des annulations

    // Champs supplémentaires pour les détails du remboursement
    private double montantRemboursement;
    private String messageConfirmation;
}
