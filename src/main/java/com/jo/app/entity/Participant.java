package com.jo.app.entity;


import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "participants")
public class Participant {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(nullable = false)
    private String nom;
    
	@Column(nullable = false)
    private String prenom;
    
	@Column(nullable = false)
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "delegation_id", nullable=false)
    private Delegation delegation;
    
    @OneToMany(mappedBy = "participant", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Resultat> resultats;

}
