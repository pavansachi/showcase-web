package com.posts.services.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posts.vo.data.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Serializable> {

	List<Tweet> findByProfileUserId(long profileId);
}
