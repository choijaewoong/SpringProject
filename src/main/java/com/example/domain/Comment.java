package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable=false) @Lob
	private String contents;
	
	@ManyToOne
	private User writer;
	
	@ManyToOne
	private Question question;
	
	public Comment() {
		
	}
	
	public Comment(User writer, Question question, String contents) {
		this.writer = writer;
		this.question = question;
		this.contents = contents;
	}
	
	public Long getId() {
		return id;
	}
	public User getWriter() {
		return writer;
	}

	public Question getQuestion() {
		return question;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}
	

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", contents=" + contents + "]";
	}	
	
}