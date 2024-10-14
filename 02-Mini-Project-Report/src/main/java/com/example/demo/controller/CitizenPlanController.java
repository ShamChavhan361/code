package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.CitizenPlanService;

@Controller
public class CitizenPlanController {
	
	
	@Autowired
	private CitizenPlanService service;
	 
	
	
	@GetMapping("/")
	public String index( Model model ) {
		
	List<String> planNames = service.getPlanName();
	
    List<String> planStatus = service.getPlanStatus();
    
    model.addAttribute("names", planNames);
    model.addAttribute("status", planStatus);
		
		System.out.println(planNames + " /" + planStatus);
		return "index";
	
	}

}
