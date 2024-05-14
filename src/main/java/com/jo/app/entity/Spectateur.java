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
@Table(name = "spectateurs")
public class Spectateur {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(nullable = false)
    private String nom;
	
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false)
    private String email;
    
    @OneToMany(mappedBy = "spectateur", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Billet> billets;
}
