package com.example.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	
	@Id @GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String title;
	
	@ManyToOne
	private User writer;
	
	@OneToMany
	private List<Comment> comments;
	
	
//	@Column(nullable=false)
//	private String writer;
	
	@Column(nullable=false) @Lob
	private String contents;
	
	public Question() {
		
	}

	public Question(String title, User writer, String content) {
		this.title = title;
		this.writer = writer;
		this.contents = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setContents(String content) {
		this.contents = content;
	}
	
	public void update(Question question) {
//		if(check(writer)) {
			this.title = question.title;
			this.contents = question.contents;			
//		}
	}	

	@Override
	public String toString() {
		return "Qna [title=" + title + ", writer=" + writer + ", contents=" + contents + "]";
	}
}
