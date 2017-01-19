package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Question {
	
	@Id @GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String writer;
	
	@Column(nullable=false) @Lob
	private String contents;
	
	public Question() {
		
	}

	public Question(String title, String userId, String content) {
		this.title = title;
		this.writer = userId;
		this.contents = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriter(String userId) {
		this.writer = userId;
	}

	public void setContents(String content) {
		this.contents = content;
	}

	@Override
	public String toString() {
		return "Qna [title=" + title + ", writer=" + writer + ", contents=" + contents + "]";
	}
}
