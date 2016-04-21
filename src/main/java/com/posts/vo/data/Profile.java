package com.posts.vo.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Profile {

	@Id
	@GeneratedValue
	private long id;
	
	@Valid
	@JsonManagedReference
	@OneToOne(mappedBy="profile", cascade=CascadeType.ALL)
	private ProfileUser user;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="UPDATE_DATE")
	private Date updateDate;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@PrePersist
	private void onUpdate() {
		
		createDate = updateDate = new Date();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProfileUser getUser() {
		return user;
	}

	public void setUser(ProfileUser user) {
		this.user = user;
	}
}
