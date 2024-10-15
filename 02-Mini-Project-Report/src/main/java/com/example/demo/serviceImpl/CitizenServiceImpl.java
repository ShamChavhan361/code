package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import com.example.demo.Entity.CitizenPlan;
import com.example.demo.binding.SearchCriteria;
import com.example.demo.repo.CitizenPlanRepo;
import com.example.demo.service.CitizenPlanService;

import io.micrometer.common.util.StringUtils;
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
		
		
		
		//Data filtering
		
		CitizenPlan entity=new CitizenPlan();
		
		if(StringUtils.isNotBlank(critetia.getPlanName()))
		{
			entity.setPlanName(critetia.getPlanName());
		}
		if(StringUtils.isNotBlank(critetia.getPlanStatus()))
		{
			entity.setPlanStatus(critetia.getPlanStatus());
		}
		if(StringUtils.isNotBlank(critetia.getGender()))
		{
			entity.setGender(critetia.getGender());
		}
		
		
		//QBE  (query by example)
		
		Example<CitizenPlan> of = Example.of(entity);
		return repo.findAll(of);
	}

	@Override
	public void geneareExcel(HttpServletResponse response) {
		
		
	}

	@Override
	public void generatePdf(HttpServletResponse response) {
		
		
	}

}
