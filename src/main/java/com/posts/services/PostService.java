package com.posts.services;

import java.util.List;

import com.posts.exceptions.PostServiceException;
import com.posts.exceptions.ProfileUserNotFoundException;
import com.posts.vo.data.Comment;
import com.posts.vo.data.Tweet;

public interface PostService {

	public List<Tweet> getAllPosts(long profileId) throws PostServiceException, ProfileUserNotFoundException;
	public List<Tweet> getAllPosts(int year) throws PostServiceException;
	
	public Tweet getPostById(Long id) throws PostServiceException;
	
	public Tweet createPost(long profileId, Tweet newPost) throws PostServiceException;
	
	public boolean deletePost(Tweet newPost) throws PostServiceException;

	public boolean likePost(long postId) throws PostServiceException;
	
	public Comment createComment(long postId, Comment newComment) throws PostServiceException;
	
	public boolean likeComment(long postId, long commentId) throws PostServiceException;
	
}
