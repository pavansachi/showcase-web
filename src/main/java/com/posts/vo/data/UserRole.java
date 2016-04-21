package com.posts.vo.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="USER_ROLE")
public class UserRole {

	@Id
	@GeneratedValue
	@Column(name="ROLE_ID")
	private long id;
	
	@Column(name="ROLE_NAME")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
