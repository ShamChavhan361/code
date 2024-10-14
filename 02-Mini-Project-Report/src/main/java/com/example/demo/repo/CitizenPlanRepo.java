package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.CitizenPlan;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {
	
	
	
	
	
	@Query("SELECT distinct(c.planName) FROM CitizenPlan c")
    List<String> findAllPlanNames();

	@Query("SELECT distinct(c.planStatus) FROM CitizenPlan c")
    List<String> findAllPlanStatus();

}
