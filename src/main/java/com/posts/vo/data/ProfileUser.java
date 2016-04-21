package com.posts.vo.data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class ProfileUser {

	@GenericGenerator(name="gen", strategy="foreign", parameters={@Parameter(name="property", value="profile")})
	@GeneratedValue(generator="gen")
	@Id
	private long id;
	
	@NotNull
	@Size(min=6, max=10)
	@Column(unique=true)
	private String username;
	
	@NotNull
	@Size(min=6, max=6)
	@JsonProperty(access=Access.WRITE_ONLY)
	@Transient
	private String password;
	
	@Column(unique=true)
	private String email;
	
//	@JsonManagedReference
//	@OneToMany(mappedBy="profileUser", cascade=CascadeType.ALL)
//	private List<Tweet> tweets;
	
//	public List<Tweet> getTweets() {
//		return tweets;
//	}
//
//	public void setTweets(List<Tweet> tweets) {
//		this.tweets = tweets;
//	}

	@OneToMany(mappedBy="profileUser", cascade=CascadeType.ALL)
	private Set<UserRole> roles;

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	@JsonProperty(access=Access.WRITE_ONLY)
	private String hashedPassword;
	
	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	private boolean isActive;
	
	@PrePersist
	public void onCreate() {
		
		isActive = true;
	}
	
	@JsonBackReference
	@OneToOne
	@PrimaryKeyJoinColumn
	private Profile profile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
