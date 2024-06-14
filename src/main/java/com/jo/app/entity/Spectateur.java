package com.jo.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "spectateur", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Billet> billets;
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user; // pour lier un participant à un utilisateur avec un/des role(s)
}
