package com.posts.services.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posts.vo.data.ProfileUser;

public interface ProfileUserRepository extends JpaRepository<ProfileUser, Serializable> {

	public ProfileUser findByUsername(String username);
}
