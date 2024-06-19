package com.jo.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jo.app.dto.RegistrationDto;
import com.jo.app.entity.Role;
import com.jo.app.entity.User;
import com.jo.app.repository.RoleRepository;
import com.jo.app.repository.UserRepository;
import com.jo.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public void saveUser(RegistrationDto registrationDto) {
		User user = new User();
		user.setName(registrationDto.getPrenom()+" "+registrationDto.getNom());
		user.setEmail(registrationDto.getEmail());
		
		//Utilisons spring security pour crypter le password 
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
        for (Role role : registrationDto.getRoles()) {
            roles.add(roleRepository.findById(role.getId()).orElse(null));
        }
        user.setRoles(roles);
		
		userRepository.save(user);
		
	}
	
	
	@Override
	public void saveSpectateur(RegistrationDto registrationDto) {
		
		User user = new User();
		user.setName(registrationDto.getPrenom()+" "+registrationDto.getNom());
		user.setEmail(registrationDto.getEmail());
		//Utilisons spring security pour crypter le password 
		user.setPassword(registrationDto.getPassword());
		
		Role role = roleRepository.findByName("SPECTATEUR");
        user.setRoles(Arrays.asList(role));
		
		userRepository.save(user);
	}


	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}


	@Override
	public User findById(Long id) {
		
		return userRepository.findById(id).get();
	}


	@Override
	public void updateUser(RegistrationDto user) {
		saveUser(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

}
