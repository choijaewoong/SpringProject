package com.example.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Comment;
import com.example.domain.CommentRepository;
import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;
import com.example.utils.HttpSessionUtils;

@RestController
@RequestMapping("/api/questions")
public class ApiQuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
//	@GetMapping("")
//	public List<Question> list() {
//		return (List<Question>)questionRepository.findAll();
//	}
//	
//	@GetMapping("/{id}")
//	public Question show(@PathVariable long id) {
//		Question question = questionRepository.findOne(id);
//		return question;
//	}
	
	@PostMapping("/{questionId}/comments/{commentId}")
	public Comment delete(@PathVariable long questionId, @PathVariable long commentId, HttpSession session) {
		
		Comment comment = commentRepository.findOne(commentId);
		checkOwner(comment.getWriter().getId(), session);
		commentRepository.delete(commentId);
		
		return comment;
	}
	
	private void checkOwner(Long userId, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인하지 않은 사용자...");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!loginUser.matchId(userId)){
			throw new IllegalStateException("다른 사용자 정보를 수정할 수 없습니다.");	
		}
	}
}
