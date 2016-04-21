package com.posts.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posts.exceptions.PostServiceException;
import com.posts.exceptions.ProfileUserNotFoundException;
import com.posts.services.PostService;
import com.posts.vo.data.Comment;
import com.posts.vo.data.ServiceResponse;
import com.posts.vo.data.Tweet;

//http://websystique.com/springmvc/spring-4-mvc-helloworld-tutorial-annotation-javaconfig-full-example/

@Controller
@RequestMapping
public class TweetController {

	@Autowired
	private PostService postService;

	@RequestMapping(path="/profile/{profileId}/posts", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> getPosts(@PathVariable("profileId") long profileId) throws PostServiceException, ProfileUserNotFoundException {

		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

		ServiceResponse response = null;

		List<Tweet> posts;
		//		try {

		posts = postService.getAllPosts(profileId);

		response = new ServiceResponse("The posts are successfully fetched", posts);

		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		//		} 
		//		catch (ProfileUserNotFoundException e) {
		//			
		//			e.printStackTrace();
		//			throw new ProfileUserNotFoundException();
		//		}
		//		catch (Exception e) {
		//			
		//			e.printStackTrace();
		//			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		//		}

	}

	@RequestMapping(path="/profile/{profileId}/post/create", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> createPost(@PathVariable("profileId") long profileId, @Valid @RequestBody Tweet newPost) {

		ServiceResponse response = null;

		try {

			newPost = postService.createPost(profileId, newPost);

			response = new ServiceResponse("The post was successfully created", newPost);
			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(path="/post/{postId}/like", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> likePost(@PathVariable("postId") long postId) {

		ServiceResponse response = null;

		try {

			boolean result = postService.likePost(postId);

			response = new ServiceResponse("", result);

			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(path="/post/{postId}/comment/create", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> createComment(@PathVariable("postId") long postId, @RequestBody Comment newComment) {

		ServiceResponse response = null;

		try {

			newComment = postService.createComment(postId, newComment);

			response = new ServiceResponse("", newComment);

			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(path="/post/{postId}/comment/{commentId}/like", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> likeComment(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {

		ServiceResponse response = null;

		try {

			boolean result = postService.likeComment(postId, commentId);

			response = new ServiceResponse("", result);

			return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ServiceResponse> handleMethodArgumentNotValidException( MethodArgumentNotValidException error ) {

		BindingResult result = error.getBindingResult();

		ServiceResponse errorResponse = null;

		List<String> errors = new ArrayList<String>();

		for (FieldError e : result.getFieldErrors() ) {

			errors.add(messageSource.getMessage(e, null));
			System.out.println (messageSource.getMessage(e, null));
		}

		if (errors.size() > 0) {

			errorResponse = new ServiceResponse("There are errors");
			errorResponse.setErrors(errors);
			return new ResponseEntity<ServiceResponse>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<ServiceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServiceResponse> handleProfileUserNotFoundException( MethodArgumentNotValidException error ) {

		ServiceResponse errorResponse = null;

		errorResponse = new ServiceResponse("The profile is unknown !");

		return new ResponseEntity<ServiceResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
