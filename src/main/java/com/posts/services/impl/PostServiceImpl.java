package com.posts.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posts.exceptions.PostDataException;
import com.posts.exceptions.PostServiceException;
import com.posts.exceptions.ProfileUserNotFoundException;
import com.posts.services.PostService;
import com.posts.services.data.PostDAO;
import com.posts.vo.data.Comment;
import com.posts.vo.data.Tweet;

@Service(value="postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDao;

	public List<Tweet> getAllPosts(long profileId) throws PostServiceException, ProfileUserNotFoundException {

		List<Tweet> postList = null;

		try {
			
			postList = postDao.getAllPosts(profileId);
		} 
		catch (ProfileUserNotFoundException e) {
			throw new ProfileUserNotFoundException(e);
		}
		catch (PostDataException e) {
			throw new PostServiceException(e);
		}

		return postList;
	}

	public List<Tweet> getAllPosts(int year) throws PostServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Tweet getPostById(Long id) throws PostServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Tweet createPost(long profileId, Tweet newPost) throws PostServiceException {

		try {
			newPost.setCreateDate(new Date());
			newPost = postDao.createPost(profileId, newPost);
		} catch (PostDataException e) {
			e.printStackTrace();
			throw new PostServiceException(e);
		}

		return newPost;
	}

	public boolean deletePost(Tweet newPost) throws PostServiceException {
		return false;
	}

	public Tweet updatePost(Tweet newPost) throws PostServiceException {
		return null;
	}

	public Comment createComment(long postId, Comment newComment) throws PostServiceException {

		try {

			newComment.setCreateDate(new Date());
			newComment = postDao.createComment(postId, newComment);
		} catch (PostDataException e) {
			e.printStackTrace();
			throw new PostServiceException(e);
		}

		return newComment;
	}

	public boolean likePost(long postId) throws PostServiceException {

		try {

			boolean result = postDao.likePost(postId);
			
			return result;
		} catch (PostDataException e) {
			e.printStackTrace();
			throw new PostServiceException(e);
		}

	}

	public boolean likeComment(long postId, long commentId) throws PostServiceException {
		
		try {

			boolean result = postDao.likeComment(postId, commentId);
			
			return result;
		} catch (PostDataException e) {
			e.printStackTrace();
			throw new PostServiceException(e);
		}
	}

}
