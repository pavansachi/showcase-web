package com.posts.vo.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Tweet {

	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Size(min=1, max=75)
	private String message;
	
	private int likes = 0;

	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}

//	@JsonBackReference
	@JsonProperty(access=Access.WRITE_ONLY)
	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private ProfileUser profileUser;
	
	public ProfileUser getProfileUser() {
		return profileUser;
	}
	public void setProfileUser(ProfileUser profileUser) {
		this.profileUser = profileUser;
	}

	private Date createDate;

	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@JsonManagedReference
	@OneToMany(mappedBy="tweetEntity", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
