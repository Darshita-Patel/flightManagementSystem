package com.teamAirlines.flightManagementSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Feedback {
	@Id
	private String username;
	@Lob
	private String content;
	
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Feedback(String username, String content) {
		super();
		this.username = username;
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
