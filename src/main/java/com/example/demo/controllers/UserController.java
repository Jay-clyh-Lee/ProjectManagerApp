package com.example.demo.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repo;
	
	// registration
	@GetMapping("/register")
	public String getRegistrationForm(@ModelAttribute("user") User user) {
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) {
				
		if ( ! user.getPassword().equals(user.getPasswordConfirmation()) ) {
			result.rejectValue("passwordConfirmation", "Matches", "Passwords must match");
		}
		
		if ( this.repo.findByEmail(user.getEmail()).isPresent() ) {
			result.rejectValue("email", "Email", "The email is already in use");
		}
		
		if ( result.hasErrors() ) return "register.jsp";
		
		this.service.create(user);
		
		return "redirect:/user/dashboard"; 
	}
	
	@GetMapping("/login")
	public String getLoginForm(
			@ModelAttribute("user") User user
			) {
		return "login.jsp";
	}
	
	@PostMapping("/login") 
	public String login(
			@Valid @ModelAttribute("user") User user,
			HttpSession session,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		
		user = this.service.authenticate(user);
		if ( user != null ) {
			session.setAttribute("user", user.getId());
			redirectAttributes.addFlashAttribute("message", String.format("Welcome back %s", user.getFname()));
			return "redirect:/user/dashboard"; 
		}
		
		model.addAttribute("message", "Invalid Credentials");
		
		return "login.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user");
		
		return "redirect:/login";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(
			HttpSession session,
			Model model
			) {
		
		Long userId = (Long) session.getAttribute("user");
		
		if ( userId == null ) return "redirect:/";
				
		User user = this.service.retrieve(userId);
		
		if ( user == null ) return "redirect:/";
		
		model.addAttribute("project", user.getProject());
		
		return "dashboard.jsp";
		
	}
	
	// get
	@GetMapping("/view/{id}")
	public String view(
			@PathVariable Long id,
			Model model
			) {
		model.addAttribute("User", this.service.retrieve(id));
		
		return "view.jsp";
	}
	
	// update
	@GetMapping("/update/{id}")
	public String edit(
			@PathVariable Long id,
			Model model
			) {
		model.addAttribute("User", this.service.retrieve(id));
		
		return "edit.jsp";
	}

	@PostMapping("/update/{id}")
	public String update(
			@Valid @ModelAttribute("item") User item,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) {
		if ( result.hasErrors() ) return "edit.jsp";
		
		this.service.update(item);
		
		redirectAttributes.addFlashAttribute("message", "It has been updated.");

		return "redirect:/view";
	}
	
	// delete
	@GetMapping("/delete/{id}")
	public String delete(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes
			) {
	
		User User = this.service.retrieve(id);
		if ( User != null ) return "redirect:/";
		
		Long user_id = User.getId();
		
		this.service.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "It has been deleted.");
		
		return String.format("redirect:/update/%d", user_id);
	}
}
	
	
