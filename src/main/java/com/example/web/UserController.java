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

import com.example.domain.User;
import com.example.domain.UserRepository;
import com.example.utils.HttpSessionUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);	
//	private List<User> users = new ArrayList<>();
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/create")
	public String create(User user) {
		userRepository.save(user);
//		users.add(user);
//		log.info("users size : " + users.size());
//		log.debug("users : " + users);

		return "redirect:/user/list";
	}
	
	@GetMapping("/list") // url 접근
	public String list(Model model) {		
		model.addAttribute("users", userRepository.findAll());
		return "/user/list"; // html 접근		
	}
	
	@GetMapping("/form")
	public String form() {	
		return "/user/form";
	}
	
	@GetMapping("/{id}/form")
	public String update(@PathVariable long id, Model model, HttpSession session) {
		checkOwner(id, session);
		
		model.addAttribute("user", userRepository.findOne(id));
		return "/user/updateForm";
	}

	@PutMapping("/{id}/update")
	public String update(@PathVariable long id, User user, HttpSession session) {
		checkOwner(id, session);
		
		User dbUser = userRepository.findOne(id);		
		dbUser.update(user);
		userRepository.save(dbUser); // 없어도 orm 프레임워크가 알아서 해줌		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {	
		return "/user/login";
	}	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		
		User user = userRepository.findByUserId(userId);
		
		if(user == null) {
			return "/user/login_failed";
		}
		if(!user.checkPassword(password)) {
			return "/user/login_failed";			
		}
		
		session.setAttribute(HttpSessionUtils.LOGIN_USER, user);		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute(HttpSessionUtils.LOGIN_USER);
		return "redirect:/";
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
