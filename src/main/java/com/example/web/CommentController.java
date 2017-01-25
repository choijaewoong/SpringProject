package com.example.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.Comment;
import com.example.domain.CommentRepository;
import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;
import com.example.utils.HttpSessionUtils;

@Controller
public class CommentController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private CommentRepository commentRepository;
	
	@PostMapping("/comments/{id}")
	public String comment(@PathVariable long id, Comment comment, HttpSession session) {
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		comment.setWriter(loginUser);		
		commentRepository.save(comment);
//		questions.add(question);
		log.info("users size : " + comment);
//		log.debug("users : " + questions);
		return "redirect:/questions/" + id;
	}

}
