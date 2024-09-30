package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.UserAccount;
import com.example.demo.serviceimpl.UserAccountServiceImpl;


@Controller
public class HomeController {
	
	
	
	  @Autowired
	  private UserAccountServiceImpl userSer;
	 
	
	@GetMapping("/")
	public String getDetails(Model model )
	{
		
		List<UserAccount> userAccs=userSer.getAllUserAccount();
		
		model.addAttribute("userAccs",userAccs);
		return "index";
	}

	
}
