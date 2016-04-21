package com.posts.services.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posts.vo.data.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Serializable> {

}
