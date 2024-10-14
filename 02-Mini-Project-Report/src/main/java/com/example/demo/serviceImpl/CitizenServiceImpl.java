package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.CitizenPlan;
import com.example.demo.binding.SearchCriteria;
import com.example.demo.repo.CitizenPlanRepo;
import com.example.demo.service.CitizenPlanService;

import jakarta.servlet.http.HttpServletResponse;


@Service
public class CitizenServiceImpl implements CitizenPlanService {

	
	@Autowired
	private CitizenPlanRepo repo;
	
	
	@Override
	public List<String> getPlanName() {
		
		return repo.findAllPlanNames();
		
	}

	@Override
	public List<String> getPlanStatus() {
	
		return repo.findAllPlanStatus();
	}

	@Override
	public List<CitizenPlan> searchCitizen(SearchCriteria critetia) {
		
		return null;
	}

	@Override
	public void geneareExcel(HttpServletResponse response) {
		
		
	}

	@Override
	public void generatePdf(HttpServletResponse response) {
		
		
	}

}
