package com.posts.services.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posts.vo.data.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Serializable> {

	public UserRole findByName (String name); 
}
