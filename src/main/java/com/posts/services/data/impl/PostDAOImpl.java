package com.posts.services.data.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posts.exceptions.PostDataException;
import com.posts.exceptions.ProfileUserNotFoundException;
import com.posts.services.data.PostDAO;
import com.posts.services.jpa.CommentRepository;
import com.posts.services.jpa.ProfileUserRepository;
import com.posts.services.jpa.TweetRepository;
import com.posts.vo.data.Comment;
import com.posts.vo.data.ProfileUser;
import com.posts.vo.data.Tweet;

@Service(value="postDao")
public class PostDAOImpl implements PostDAO {

	@Autowired
	private TweetRepository tweetRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private ProfileUserRepository profileUserRepo;

	public Tweet createPost(long profileId, Tweet postToCreate) throws PostDataException {


		try {

			ProfileUser profileUserEntity = profileUserRepo.findOne(profileId);

			postToCreate.setProfileUser(profileUserEntity);

			tweetRepo.save(postToCreate);

			return postToCreate;

		} catch (Exception e) {

			e.printStackTrace();
			throw new PostDataException(e);
		}

	}

	public List<Tweet> getAllPosts(long profileId) throws PostDataException, ProfileUserNotFoundException {

		List<Tweet> postList = null;

		try {

			ProfileUser user = profileUserRepo.findOne(profileId);

			if (user == null) {
				throw new ProfileUserNotFoundException();
			}

			postList = tweetRepo.findByProfileUserId(profileId);

			return postList;
		} 
		catch (ProfileUserNotFoundException e) {

			e.printStackTrace();
			throw new ProfileUserNotFoundException(e);
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new PostDataException(e);
		}

	}

	public Comment createComment(long postId, Comment commentToCreate) throws PostDataException {

		try {

			Tweet tweetEntity = getPost(postId);

			commentToCreate.setTweetEntity(tweetEntity);

			commentRepo.save(commentToCreate);

			return commentToCreate;

		} catch (Exception e) {

			e.printStackTrace();
			throw new PostDataException(e);
		}

	}

	public Tweet getPost(long postId) throws PostDataException {

		try {

			Tweet tweetEntity = tweetRepo.findOne(postId);

			return tweetEntity;

		} catch (Exception e) {

			e.printStackTrace();
			throw new PostDataException(e);
		}
	}

	public boolean likePost(long postId) throws PostDataException {

		try {

			Tweet tweetEntity = tweetRepo.findOne(postId);

			tweetEntity.setLikes(tweetEntity.getLikes() + 1);

			tweetRepo.save(tweetEntity);

			return true;

		} catch (Exception e) {

			e.printStackTrace();
			throw new PostDataException(e);
		}

	}

	public boolean likeComment(long postId, long commentId) throws PostDataException {

		try {

			Comment commentEntity = commentRepo.findOne(commentId);

			commentEntity.setLikes(commentEntity.getLikes() + 1);

			commentRepo.save(commentEntity);

			return true;

		} catch (Exception e) {

			e.printStackTrace();
			throw new PostDataException(e);
		}
	}

}
