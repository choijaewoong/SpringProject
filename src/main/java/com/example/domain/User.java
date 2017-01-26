package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id @GeneratedValue
	private long id;
	
	@Column(length=15, nullable=false, unique=true)
	private String userId;
	
	@Column(length=16, nullable=false)
	private String password;
	
	@Column(length=20, nullable=false)
	private String name;
	
	@Column(length=40, nullable=false)
	private String email;
	
	public User() {
		
	}
	
	public User(String userId, String password, String name, String email) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	public void update(User user) {
		if(checkPassword(user.password)) {
			this.name = user.name;
			this.email = user.email;			
		}
	}	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}

	public boolean matchId(long id) {
		return this.id == id;
	}	
}
