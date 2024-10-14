package com.example.demo.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.CitizenPlan;
import com.example.demo.repo.CitizenPlanRepo;

@Component
public class DataLoader implements ApplicationRunner {

	
	@Autowired
	private CitizenPlanRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//truncate data 
		repo.deleteAll();
		
		CitizenPlan p1=new CitizenPlan("SHAM", "a@gmail.com", 12345L, 123L, "Male" , "Cash", "Appoved", LocalDate.now(), LocalDate.now().plusMonths(6));
		
		CitizenPlan p2=new CitizenPlan("RAM", "b@gmail.com", 12345L, 123L, "Male","Cash", "Denied", null, null);
		
		CitizenPlan p3=new CitizenPlan("Cathy", "c@gmail.com", 12345L, 123L, "Fe-male" , "Food", "Appoved", LocalDate.now(), LocalDate.now().plusMonths(6));
		
		CitizenPlan p4=new CitizenPlan("John", "d@gmail.com", 12345L, 123L, "Male" , "Food", "Appoved", LocalDate.now(), LocalDate.now().plusMonths(6));
		
		List<CitizenPlan> records = Arrays.asList(p1,p2,p3,p4);
		
		repo.saveAll(records);
		
	}
	

}
