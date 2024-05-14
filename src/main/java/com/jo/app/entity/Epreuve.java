package com.jo.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "epreuve")
public class Epreuve {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(nullable = false)
    private String nom;
    
	@DateTimeFormat(pattern = "MM/dd/yyyy hh:mm a")
	@Column(nullable = false)
    private LocalDateTime date;
    
    @Column(nullable = false)
    private int nombrePlacesMisesEnVente;
    
    @ManyToOne
    @JoinColumn(name = "infrastructure_id", nullable = false)
    private InfrastructureSportive infrastructureSportive;
    
    @OneToMany(mappedBy = "epreuve", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Billet> billets;
    
    @OneToMany(mappedBy = "epreuve", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Resultat> resultats;
}
