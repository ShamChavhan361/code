package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.CitizenPlan;
import com.example.demo.binding.SearchCriteria;
import com.example.demo.service.CitizenPlanService;

@Controller
public class CitizenPlanController {
	
	
	@Autowired
	private CitizenPlanService service;
	 
	
	
	@GetMapping("/")
	public String index( Model model ,@ModelAttribute("SearchCriteria") SearchCriteria searchCitizen ) {
		
	
		forminit(model);

    model.addAttribute("search", new SearchCriteria());
	
		return "index";
	
	}
	
	@PostMapping("/filter")
	public String filterRecords(@ModelAttribute("SearchCriteria") SearchCriteria searchCitizen,Model model )
	{
	
		System.out.println( searchCitizen );
		
		
		
		  List<CitizenPlan> citizenPlans = service.searchCitizen(searchCitizen);
		    
		    model.addAttribute("citizenPlans", citizenPlans);
		
		    
		    model.addAttribute("search", new SearchCriteria());
		    
		forminit(model);
  
		
		
		return "index";
		
	}

	private void forminit(Model model) {
		List<String> planNames = service.getPlanName();
		
	    List<String> planStatus = service.getPlanStatus();
	    
	  
	    
	    model.addAttribute("names", planNames);
	    model.addAttribute("status", planStatus);
	}

}
