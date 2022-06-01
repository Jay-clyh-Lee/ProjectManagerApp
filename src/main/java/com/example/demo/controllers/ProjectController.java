package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.example.demo.models.Project;
import com.example.demo.models.UserProject;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.services.ProjectService;
import com.example.demo.services.ArtistService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("project")
public class ProjectController {
	
	@Autowired
	private ProjectService service;
	
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private UserService userService;
	
	// home
	@GetMapping("/")
	public String home() {
		return "home.jsp";
	}

	// add
	@GetMapping("/add")
	public String getAddForm(@ModelAttribute("item") Project item) {
		return "add.jsp";
	}
	
	@PostMapping("/add")
	public String add(
			@Valid @ModelAttribute("item") Project item,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			HttpSession session
			) {
		
		User user = this.userService.retrieveInSessionUser(session);
		if ( user == null ) return "redirect:/user/login";
		
		// direct back to add page
		if ( result.hasErrors() ) return "add.jsp";
		
		
		item.setUser(user);
		this.service.create(item);
		
		redirectAttributes.addFlashAttribute("message", "It has been added.");
		
		return "redirect:/view";
	}
	
	// get
	@GetMapping("/view/{id}")
	public String view(
			@PathVariable Long id,
			Model model
			) {

		model.addAttribute("project", this.service.retrieve(id));
		
		return "view.jsp";
	}
	
	// update
	@GetMapping("/update/{id}")
	public String edit(
			@PathVariable Long id,
			Model model,
			HttpSession session
			) {
		
		User user = this.userService.retrieveInSessionUser(session);
		if ( user == null ) return "redirect:/user/login";
		
		// load the item to delete
		Project project = null;
		try {
			project= this.service.retrieve(id);
		} catch (Exception e) {
			return "redirect:/user/login";
		}
		
		if ( project.getUser().getId() != user.getId() ) return "redirect:/user/logout";

		if ( project.getUserProject().size() > 0 ) {
			List<Long> excludedArtists = new ArrayList<Long>();
			
			for ( UserProject projectArtist : project.getUserProject() ) {
				excludedArtists.add(projectArtist.getArtist().getId());
			}
			
			model.addAttribute("artist", this.artistService.allExcluding(excludedArtists));
		} else {
			model.addAttribute("artist", this.artistService.getAll());
		}	
		
		model.addAttribute("project", this.service.retrieve(id));
		model.addAttribute("track", new Task());
		model.addAttribute("artist", this.service.getAll());
		
		return "edit.jsp";
	}

	@PostMapping("/update/{id}")
	public String update(
			@Valid @ModelAttribute("item") Project item,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) {
		if ( result.hasErrors() ) return "edit.jsp";
		
		this.service.update(item);
		
		redirectAttributes.addFlashAttribute("message", "It has been updated.");

		return "redirect:/edit";
	}
	
	// delete
	@GetMapping("/delete/{id}")
	public String delete(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes,
			HttpSession session
			) {
		
		User user = this.userService.retrieveInSessionUser(session);
		if ( user == null ) return "redirect:/user/login";
		
		// load the item to delete
		Project project = null;
		try {
			project = this.service.retrieve(id);
		} catch (Exception e) {
			return "redirect:/user/login";
		}
		
		if ( project.getUser().getId() != user.getId() ) return "redirect:/user/logout";
				
		this.service.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "It has been deleted.");
		
		return "redirect:/dashboard";
	}
}
	
	
