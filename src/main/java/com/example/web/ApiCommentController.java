package com.example.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Comment;
import com.example.domain.CommentRepository;
import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;
import com.example.utils.HttpSessionUtils;

@RestController
public class ApiCommentController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	@PostMapping("/api/questions/{questionId}/answers")
	public Comment create(@PathVariable long questionId, String contents, HttpSession session) {
	
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(questionId);
		Comment comment = new Comment(loginUser, question, contents);
		return commentRepository.save(comment);		
	}
}
