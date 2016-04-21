package com.posts.services.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posts.vo.data.Comment;

public interface CommentRepository extends JpaRepository<Comment, Serializable> {

	
	
}
