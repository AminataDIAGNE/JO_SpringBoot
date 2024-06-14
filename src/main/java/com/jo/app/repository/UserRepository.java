package com.jo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jo.app.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
