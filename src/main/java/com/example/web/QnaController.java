package com.example.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;
import com.example.utils.HttpSessionUtils;

@Controller
public class QnaController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);	
//	private List<Question> questions = new ArrayList<>();
	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	@PostMapping("/qna/questions")
	public String questions(Question question) {
		questionRepository.save(question);
//		questions.add(question);
//		log.info("users size : " + questions.size());
//		log.debug("users : " + questions);
		return "redirect:/";
	}
	
	@GetMapping("/qna/form")
	public String form(Model model, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
//			throw new IllegalStateException("로그인하지 않은 사용자...");
			return "/user/login";
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		model.addAttribute("user", loginUser);
		return "/qna/form";
	}
	
	@GetMapping("/qna/detail/{id}")
	public String detail(@PathVariable long id, Model model) {
		
		Question question = questionRepository.findOne(id);
		model.addAttribute("question", question);
		return "/qna/show";		
	}
	
	@GetMapping("/qna/update/{id}")
	public String update(@PathVariable long id, Model model, HttpSession session){
		Question question= questionRepository.findOne(id);
		model.addAttribute("question", question);		
		//checkOwner(id, session);
		
		return "/qna/update";		
	}

	@PutMapping("/qna/update/{id}")
	public String update(@PathVariable long id, Question question, HttpSession session) {
//		checkOwner(id, session);
		
		Question oldQuestion= questionRepository.findOne(id);
		oldQuestion.update(question);
		
		questionRepository.save(oldQuestion); // 없어도 orm 프레임워크가 알아서 해줌		
		return "redirect:/";
	}
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("questions", questionRepository.findAll());
		return "/index";
	}
	
	private void checkOwner(long id, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인하지 않은 사용자...");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!loginUser.matchId(id)){
			throw new IllegalStateException("다른 사용자 정보를 수정할 수 없습니다.");	
		}
	}
}
