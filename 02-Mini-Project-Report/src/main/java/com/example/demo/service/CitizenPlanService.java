package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.CitizenPlan;
import com.example.demo.binding.SearchCriteria;

import jakarta.servlet.http.HttpServletResponse;

public interface CitizenPlanService {
	
	
	public List<String> getPlanName();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> searchCitizen(SearchCriteria critetia);
	
	public void geneareExcel(HttpServletResponse response);
	
	public void generatePdf(HttpServletResponse response);
	
	

}
