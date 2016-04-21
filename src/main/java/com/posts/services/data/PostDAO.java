package com.posts.services.data;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.posts.exceptions.PostDataException;
import com.posts.exceptions.ProfileUserNotFoundException;
import com.posts.vo.data.Comment;
import com.posts.vo.data.Tweet;

public interface PostDAO {

	@Transactional
	public Tweet createPost(long profileId, Tweet newPost) throws PostDataException;
	
	public List<Tweet> getAllPosts(long profileId) throws PostDataException, ProfileUserNotFoundException;
	
	@Transactional
	public Tweet getPost(long id) throws PostDataException;
	
	@Transactional
	public boolean likePost(long id) throws PostDataException;
	
	@Transactional
	public Comment createComment(long postId, Comment newPost) throws PostDataException;

	@Transactional
	public boolean likeComment(long postId, long commentId) throws PostDataException;
}
