package com.jo.app.service;

import java.util.List;

import com.jo.app.entity.Role;

public interface RoleService {


	Role findByName(String email);

	List<Role> findAllRoles();

	Role findById(Long id);

	void saveRole(Role role);

	void updateRole(Role role);

	void deleteRole(Long id);
}
