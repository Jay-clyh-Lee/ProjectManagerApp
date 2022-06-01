package com.example.demo.controllers;

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

import com.example.demo.models.Task;
import com.example.demo.services.TaskService;

@Controller
@RequestMapping("task")
public class TaskController {
	
	@Autowired
	private TaskService service;

	// add
	@GetMapping("/add/{project_id}")
	public String getAddForm(@ModelAttribute("item") Task item) {
		return "add.jsp";
	}
	
	@PostMapping("/add/{project_id}")
	public String add(
			@PathVariable Long project_id,
			@Valid @ModelAttribute("item") Task item,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) {
		// direct back to add page
		if ( !result.hasErrors() ) {
			item = this.service.create(item, project_id);
			if ( item == null ) return "redirect:/";
		}
		
		redirectAttributes.addFlashAttribute("message", "It has been added.");
		
		return String.format("redirect:/update/%d", project_id);
	}
	
	// get
	@GetMapping("/view/{id}")
	public String view(
			@PathVariable Long id,
			Model model
			) {
		model.addAttribute("Task", this.service.retrieve(id));
		
		return "view.jsp";
	}
	
	// update
	@GetMapping("/update/{id}")
	public String edit(
			@PathVariable Long id,
			Model model
			) {
		model.addAttribute("Task", this.service.retrieve(id));
		
		return "edit.jsp";
	}

	@PostMapping("/update/{id}")
	public String update(
			@Valid @ModelAttribute("item") Task item,
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
	
		Task task = this.service.retrieve(id);
		if ( task != null ) return "redirect:/";
		
		Long project_id = task.getProject().getId();
		
		this.service.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "It has been deleted.");
		
		return String.format("redirect:/update/%d", project_id);
	}
}
	
	
