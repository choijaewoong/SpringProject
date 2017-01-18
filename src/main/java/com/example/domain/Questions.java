package com.example.domain;

public class Questions {
	
	private String title;
	private String writer;
	private String contents;
	
	public Questions() {
		
	}

	public Questions(String title, String userId, String content) {
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
