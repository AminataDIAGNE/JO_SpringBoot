package com.jo.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "delegation")
public class Delegation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nom;
	
    private int nombreMedaillesOr;
    
    private int nombreMedaillesArgent;
     
    private int nombreMedaillesBronze;


    @OneToMany(mappedBy = "delegation", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Participant> participants;
}
