package com.example.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;

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
	public String form() {	
		return "/qna/form";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("questions", questionRepository.findAll());
		return "/index";
	}
}
