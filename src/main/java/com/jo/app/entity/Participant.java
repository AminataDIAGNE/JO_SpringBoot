package com.jo.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Delegation delegation;

    @JsonManagedReference
    @OneToMany(mappedBy = "participant", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Resultat> resultats;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "epreuve_participant",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "epreuve_id")
    )
    private List<Epreuve> epreuves;
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user; // pour lier un participant à un utilisateur avec un/des role(s)
}
