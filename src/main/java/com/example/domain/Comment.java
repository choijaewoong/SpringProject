package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id @GeneratedValue
	private long id;
	
	@Column(nullable=false) @Lob
	private String contents;
	
	@ManyToOne
	private User writer;
	
	@ManyToOne
	private Question question;
	
	private Comment() {
		
	}
	
	public Comment(String contents) {
		this.contents = contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", contents=" + contents + "]";
	}	
	
}