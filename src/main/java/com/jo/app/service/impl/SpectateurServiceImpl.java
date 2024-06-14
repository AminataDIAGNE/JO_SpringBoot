package com.jo.app.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.SpectateurDto;
import com.jo.app.entity.Role;
import com.jo.app.entity.Spectateur;
import com.jo.app.entity.User;
import com.jo.app.mapper.SpectateurMapper;
import com.jo.app.repository.RoleRepository;
import com.jo.app.repository.SpectateurRepository;
import com.jo.app.repository.UserRepository;
import com.jo.app.service.SpectateurService;

@Service
public class SpectateurServiceImpl implements SpectateurService{
    
	private SpectateurRepository spectateurRepository;

    //private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
	private RoleRepository roleRepository;
	

    public SpectateurServiceImpl(SpectateurRepository spectateurRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.spectateurRepository = spectateurRepository;
        //this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
		this.roleRepository = roleRepository;
    }

	@Override
	public List<SpectateurDto> findAllSpectateurs() {
		List<Spectateur> spectateurs = spectateurRepository.findAll();
        return spectateurs.stream().map(SpectateurMapper::mapToSpectateurDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createSpectateur(SpectateurDto spectateurDto) {
		// Créer un nouvel utilisateur
        User user = new User();
        user.setName(spectateurDto.getPrenom() +" "+ spectateurDto.getNom());
        user.setEmail(spectateurDto.getEmail());
        user.setPassword(spectateurDto.getPassword());
        //user.setPassword(passwordEncoder.encode(spectateurDto.getPassword()));
        
        // Attribuer le rôle de spectateur
        Role role = roleRepository.findByName("ROLE_SPECTATEUR");
        user.setRoles(Arrays.asList(role));
        
        // Enregistrer l'utilisateur
        userRepository.save(user);
        
        // Associer l'utilisateur au spectateur
        spectateurDto.setUser(user);
        
		Spectateur spectateur = SpectateurMapper.mapToSpectateur(spectateurDto);
		spectateurRepository.save(spectateur);
		
	}

	@Override
	public SpectateurDto findSpectateurById(Long spectateurId) {
		Spectateur spectateur = spectateurRepository.findById(spectateurId).get();
		return SpectateurMapper.mapToSpectateurDto(spectateur);
	}

	@Override
	public void updateSpectateur(SpectateurDto spectateurDto) {
		Spectateur spectateur = SpectateurMapper.mapToSpectateur(spectateurDto);
		spectateurRepository.save(spectateur);
		
	}

	@Override
	public void deleteSpectateur(Long spectateurId) {
		spectateurRepository.deleteById(spectateurId);
		
	}

	@Override
	public SpectateurDto findByEmail(String email) {
		
		if(spectateurRepository.findByEmail(email) != null) {
			return SpectateurMapper.mapToSpectateurDto(spectateurRepository.findByEmail(email));
		}
		return null;
	}


}
