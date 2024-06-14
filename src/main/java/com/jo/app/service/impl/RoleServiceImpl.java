package com.jo.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jo.app.entity.Role;
import com.jo.app.repository.RoleRepository;
import com.jo.app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	private RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role findByName(String email) {
		return roleRepository.findByName(email);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id).get();
	}

	@Override
	public void saveRole(Role role) {
		roleRepository.save(role);
		
	}

	@Override
	public void updateRole(Role role) {
		roleRepository.save(role);
		
	}

	@Override
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
		
	}

}
