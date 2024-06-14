package com.jo.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jo.app.entity.Billet;
import com.jo.app.entity.Delegation;
import com.jo.app.entity.Epreuve;
import com.jo.app.entity.InfrastructureSportive;
import com.jo.app.entity.Participant;
import com.jo.app.entity.Resultat;
import com.jo.app.entity.Role;
import com.jo.app.entity.Spectateur;
import com.jo.app.entity.User;
import com.jo.app.repository.BilletRepository;
import com.jo.app.repository.DelegationRepository;
import com.jo.app.repository.EpreuveRepository;
import com.jo.app.repository.InfrastructureSportiveRepository;
import com.jo.app.repository.ParticipantRepository;
import com.jo.app.repository.ResultatRepository;
import com.jo.app.repository.RoleRepository;
import com.jo.app.repository.SpectateurRepository;
import com.jo.app.repository.UserRepository;
import com.jo.app.util.Etat;


@SpringBootApplication
public class JOApplication implements CommandLineRunner{

	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private DelegationRepository delegationRepository;
	
	@Autowired
	private ResultatRepository resultatRepository;
	
	@Autowired
	private InfrastructureSportiveRepository infrastructureSportiveRepository;
	
	@Autowired
	private EpreuveRepository epreuveRepository;
	
	@Autowired
	private SpectateurRepository spectateurRepository;
	
	@Autowired
	private BilletRepository billetRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JOApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LocalDate date = LocalDate.of(2024, 4, 30);
		LocalTime time = LocalTime.of(12, 30);
		LocalDateTime dateTimeFromParts = LocalDateTime.of(date, time);
		
		// Insérer des délégationss
        Delegation delegation1 = new Delegation();
        delegation1.setNom("Delegation 1");
        Delegation delegation2 = new Delegation();
        delegation2.setNom("Delegation 2");
        delegationRepository.saveAll(Arrays.asList(delegation1, delegation2));

        // Insérer des participants
        Participant participant1 = new Participant();
        participant1.setDelegation(delegation1);
        participant1.setNom("Sarr");
        participant1.setPrenom("Fatou");
        participant1.setEmail("salioundao21@gmail.com");
        
        Participant participant2 = new Participant();
        participant2.setDelegation(delegation2);
        participant2.setNom("Ndiaye");
        participant2.setPrenom("Bara");
        participant2.setEmail("barandiaye12@gmail.com");
        
        Participant participant3 = new Participant();
        participant3.setDelegation(delegation2);
        participant3.setNom("Bassat");
        participant3.setPrenom("Aness");
        participant3.setEmail("annesbassat@gmail.com");
        participantRepository.saveAll(Arrays.asList(participant1, participant2, participant3));
        
        // Insérer des Infrastructure Sportives
        InfrastructureSportive infras1 = new InfrastructureSportive();
        infras1.setNom("Stade de France");
        infras1.setAdresse("Paris Bercy");
        infras1.setCapacite(60000);
        infrastructureSportiveRepository.saveAll(Arrays.asList(infras1));
        
        // Insérer des Epreuves
        Epreuve epreuve1 = new Epreuve();
        epreuve1.setDate(dateTimeFromParts);
        epreuve1.setNom("Jeux Olympique");
        epreuve1.setNombrePlacesMisesEnVente(10);
        epreuve1.setPrixUnitaireBillet(10.);
        epreuve1.setInfrastructureSportive(infras1);
        epreuveRepository.saveAll(Arrays.asList(epreuve1));
        
        // Insérer des Resultats
        Resultat resultat1 = new Resultat();
        resultat1.setParticipant(participant1);
        resultat1.setPosition(2);
        resultat1.setTempsPoints(10);
        resultat1.setEpreuve(epreuve1);
        
        Resultat resultat2 = new Resultat();
        resultat2.setParticipant(participant1);
        resultat2.setPosition(3);
        resultat2.setTempsPoints(11);
        resultat2.setEpreuve(epreuve1);
        
        Resultat resultat3 = new Resultat();
        resultat3.setParticipant(participant2);
        resultat3.setPosition(1);
        resultat3.setTempsPoints(9);
        resultat3.setEpreuve(epreuve1);
        resultatRepository.saveAll(Arrays.asList(resultat1, resultat2, resultat3));
        
        Spectateur spectateur1 = new Spectateur();
        spectateur1.setNom("Danet");
        spectateur1.setPrenom("Chloé");
        spectateur1.setEmail("chloe@gmail.com");
        spectateurRepository.saveAll(Arrays.asList(spectateur1));
        
        Billet billet1 = new Billet();
        billet1.setEpreuve(epreuve1);
        billet1.setSpectateur(spectateur1);
        billet1.setQuantite(2);
        billet1.setPrixTotal(epreuve1.getPrixUnitaireBillet()*billet1.getQuantite());
        billet1.setEtat(Etat.VALIDE);
        billetRepository.saveAll(Arrays.asList(billet1));
        
        
        User user = new User();
        user.setName("Aminata Diagne");
        user.setEmail("amina@gmail.com");
        user.setPassword("1234");
        //user.setRoles(Arrays.asList(role1, role2, role3, role4));
        userRepository.saveAll(Arrays.asList(user));
        
        Role role1 = new Role();
        role1.setName(com.jo.app.util.Role.CONTROLEUR.getLibelle());
        Role role2 = new Role();
        role2.setName(com.jo.app.util.Role.ORGANISATEUR.getLibelle());
        Role role3= new Role();
        role3.setName(com.jo.app.util.Role.SPECTATEUR.getLibelle());
        Role role4= new Role();
        role4.setName(com.jo.app.util.Role.PARTICIPANT.getLibelle());
        roleRepository.saveAll(Arrays.asList(role1, role2, role3, role4));
        
        user.setRoles(Arrays.asList(role1, role2, role3, role4));
        userRepository.saveAndFlush(user);
        
        // fonctionne si la methode est annotée comme étant transactionnelle
        //participantRepository.deleteById(participant3.getId());
        //delegationRepository.deleteById(delegation2.getId());

	}

}
