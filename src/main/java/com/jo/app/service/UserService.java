package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.RegistrationDto;
import com.jo.app.entity.User;

public interface UserService {

	void saveUser(RegistrationDto registrationDto);
	
	void saveSpectateur(RegistrationDto registrationDto);

	User findByEmail(String email);

	List<User> findAllUsers();

	User findById(Long id);


	void updateUser(RegistrationDto user);

	void deleteUser(Long id);

	void save(User user);
}
