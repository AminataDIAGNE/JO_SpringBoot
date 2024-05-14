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
@Table(name = "infrastructure_sportive")
public class InfrastructureSportive {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String adresse;
    private int capacite;
    
    @OneToMany(mappedBy = "infrastructureSportive", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Epreuve> epreuves;
}
