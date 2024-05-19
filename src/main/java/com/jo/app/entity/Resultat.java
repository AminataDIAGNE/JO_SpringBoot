package com.jo.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resultat")
public class Resultat {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	    
	 @ManyToOne
	 @JoinColumn(name = "epreuve_id")
	 private Epreuve epreuve;
	    
	 @ManyToOne
	 @JoinColumn(name = "participant_id")
	 private Participant participant;
	    
	 private int tempsPoints;
	 
	 private int position;

	 boolean forfait ;
	
}
